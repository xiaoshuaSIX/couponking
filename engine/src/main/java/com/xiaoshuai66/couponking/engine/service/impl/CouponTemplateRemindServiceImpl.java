package com.xiaoshuai66.couponking.engine.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai66.couponking.engine.common.context.UserContext;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateRemindDO;
import com.xiaoshuai66.couponking.engine.dao.mapper.CouponTemplateRemindMapper;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateQueryReqDTO;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRemindCreateReqDTO;
import com.xiaoshuai66.couponking.engine.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.engine.mq.event.CouponTemplateRemindDelayEvent;
import com.xiaoshuai66.couponking.engine.mq.producer.CouponTemplateRemindDelayProducer;
import com.xiaoshuai66.couponking.engine.service.CouponTemplateRemindService;
import com.xiaoshuai66.couponking.engine.service.CouponTemplateService;
import com.xiaoshuai66.couponking.engine.toolkit.CouponTemplateRemindUtil;
import com.xiaoshuai66.couponking.framework.exception.ClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @className: CouponTemplateRemindServiceImpl
 * @Description: 优惠券预约提醒业务逻辑实现层
 * @author: zhaoshuai
 * @date: 2025/2/27 14:43
 */
@Service
@RequiredArgsConstructor
public class CouponTemplateRemindServiceImpl extends ServiceImpl<CouponTemplateRemindMapper, CouponTemplateRemindDO> implements CouponTemplateRemindService {

    private final CouponTemplateService couponTemplateService;
    private final CouponTemplateRemindMapper couponTemplateRemindMapper;
    private final CouponTemplateRemindDelayProducer couponTemplateRemindDelayProducer;

    @Override
    @Transactional
    public void createCouponRemind(CouponTemplateRemindCreateReqDTO requestParam) {
        // 验证优惠券是否存在，避免缓存穿透问题并获取优惠券开抢时间
        CouponTemplateQueryRespDTO couponTemplate = couponTemplateService.findCouponTemplate(new CouponTemplateQueryReqDTO(requestParam.getShopNumber(), requestParam.getCouponTemplateId()));

        // 查询用户是否已经预约过优惠券提醒信息
        LambdaQueryWrapper<CouponTemplateRemindDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateRemindDO.class)
                .eq(CouponTemplateRemindDO::getUserId, UserContext.getUserId())
                .eq(CouponTemplateRemindDO::getCouponTemplateId, requestParam.getCouponTemplateId());
        CouponTemplateRemindDO couponTemplateRemindDO = couponTemplateRemindMapper.selectOne(queryWrapper);

        // 如果没创建过提醒
        if (couponTemplateRemindDO == null) {
            couponTemplateRemindDO = BeanUtil.toBean(requestParam, CouponTemplateRemindDO.class);

            // 设置优惠券开抢时间信息
            couponTemplateRemindDO.setStartTime(couponTemplate.getValidStartTime());
            couponTemplateRemindDO.setInformation(CouponTemplateRemindUtil.calculateBitMap(requestParam.getRemindTime(), requestParam.getType()));
            couponTemplateRemindDO.setUserId(Long.valueOf(UserContext.getUserId()));

            couponTemplateRemindMapper.insert(couponTemplateRemindDO);
        } else {
            Long information = couponTemplateRemindDO.getInformation();
            Long bitMap = CouponTemplateRemindUtil.calculateBitMap(requestParam.getRemindTime(), requestParam.getType());
            if ((information & bitMap) != 0L) {
                throw new ClientException("已经创建过该提醒了");
            }
            couponTemplateRemindDO.setInformation(information ^ bitMap);

            couponTemplateRemindMapper.update(couponTemplateRemindDO, queryWrapper);
        }

        // 发送预约提醒抢购优惠券延时消息
        CouponTemplateRemindDelayEvent couponTemplateRemindDelayEvent = CouponTemplateRemindDelayEvent.builder()
                .couponTemplateId(couponTemplate.getId())
                .userId(UserContext.getUserId())
                .contact(UserContext.getUserId())
                .shopNumber(couponTemplate.getShopNumber())
                .type(requestParam.getType())
                .remindTime(requestParam.getRemindTime())
                .startTime(couponTemplate.getValidStartTime())
                .delayTime(DateUtil.offsetMinute(couponTemplate.getValidStartTime(), -requestParam.getRemindTime()).getTime())
                .build();
        couponTemplateRemindDelayProducer.sendMessage(couponTemplateRemindDelayEvent);
    }
}
