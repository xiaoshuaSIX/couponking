package com.xiaoshuai66.couponking.distribution.service.handler.excel;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.xiaoshuai66.couponking.distribution.common.constant.EngineRedisConstant;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponSourceEnum;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponStatusEnum;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponTaskStatusEnum;
import com.xiaoshuai66.couponking.distribution.dao.entity.CouponTaskDO;
import com.xiaoshuai66.couponking.distribution.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.distribution.dao.entity.UserCouponDO;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTaskMapper;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.distribution.dao.mapper.UserCouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

/**
 * @author 赵帅
 * @ClassName: ReadExcelDistributionListener.java
 * @Description: 优惠券任务读取 Excel 分发监听器
 * @Create 2025/2/18 14:44
 */
@RequiredArgsConstructor
public class ReadExcelDistributionListener extends AnalysisEventListener<CouponTaskExcelObject> {

    private final Long couponTaskId;
    private final CouponTemplateDO couponTemplateDO;
    private final StringRedisTemplate stringRedisTemplate;
    private final CouponTemplateMapper couponTemplateMapper;
    private final UserCouponMapper userCouponMapper;
    private final CouponTaskMapper couponTaskMapper;

    @Override
    public void invoke(CouponTaskExcelObject data, AnalysisContext analysisContext) {
        // 通过缓存判断优惠券模板记录缓存是否充足
        String couponTemplateKey = String.format(EngineRedisConstant.COUPON_TEMPLATE_KEY, couponTemplateDO.getId());
        Long stock = stringRedisTemplate.opsForHash().increment(couponTemplateKey, "stock", -1);
        if (stock < 0) {
            // 优惠券模板缓存库存不足扣减失败
            return;
        }

        // 扣减优惠券模板库存，如果扣减成功，这里会返回 1，代表修改记录成功；否则返回 0，代表没有修改成功
        int decrementResult = couponTemplateMapper.decrementCouponTemplateStock(couponTemplateDO.getShopNumber(), couponTemplateDO.getId(), 1);
        if (!SqlHelper.retBool(decrementResult)) {
            // 优惠券模板数据库库存不足扣减失败
            return;
        }

        // 添加用户领券记录到数据库
        Date now = new Date();
        // 解析优惠券有效期，例如 规则设置的为48小时，则再当前时间now + 48小时，算出有效期的结束时间
        DateTime validEndTime = DateUtil.offsetHour(now, JSON.parseObject(couponTemplateDO.getConsumeRule()).getInteger("validityPeriod"));
        UserCouponDO userCouponDO = UserCouponDO.builder()
                .couponTemplateId(couponTemplateDO.getId())
                .userId(Long.parseLong(data.getUserId()))
                .receiveTime(now)
                .receiveCount(1) // 代表第一次领取该优惠券
                .validEndTime(validEndTime)
                .source(CouponSourceEnum.PLATFORM.getType())
                .status(CouponStatusEnum.EFFECTIVE.getType())
                .createTime(new Date())
                .updateTime(new Date())
                .delFlag(0)
                .build();
        try {
            userCouponMapper.insert(userCouponDO);
        } catch (Exception ex) {
            // 用户已经领取优惠券，会被唯一索校验住，直接返回即可
            return;
        }

        // 添加优惠券到用户已领取的 Redis 优惠券列表中
        String userCouponListCacheKey = String.format(EngineRedisConstant.USER_COUPON_TEMPLATE_LIST_KEY, data.getUserId());
        String userCouponItemCacheKey = StrUtil.builder()
                .append(couponTemplateDO.getId())
                .append("_")
                .append(userCouponDO.getId())
                .toString();
        stringRedisTemplate.opsForZSet().add(userCouponListCacheKey, userCouponItemCacheKey, now.getTime());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 确保所有用户都已经接到优惠券后，设置优惠券推送任务完成时间
        CouponTaskDO couponTaskDO = CouponTaskDO.builder()
                .id(couponTaskId)
                .status(CouponTaskStatusEnum.SUCCESS.getStatus())
                .completionTime(new Date())
                .build();
        couponTaskMapper.updateById(couponTaskDO);
    }
}
