package com.xiaoshuai66.couponking.merchant.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai66.couponking.merchant.admin.constant.MerchantAdminRedisConstant;
import com.xiaoshuai66.couponking.merchant.admin.context.UserContext;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateLogDO;
import com.xiaoshuai66.couponking.merchant.admin.dao.mapper.CouponTemplateLogMapper;
import com.xiaoshuai66.couponking.merchant.admin.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.merchant.admin.enums.CouponTemplateStatusEnum;
import com.xiaoshuai66.couponking.merchant.admin.enums.DiscountTargetEnum;
import com.xiaoshuai66.couponking.merchant.admin.enums.DiscountTypeEnum;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTemplateService;
import com.xiaoshuai66.couponking.merchant.admin.service.basics.chain.MerchantAdminChainContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.xiaoshuai66.couponking.merchant.admin.enums.ChainBizMarkEnum.MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY;

/**
 * ClassName: CouponTemplateServiceImpl
 * Package: com.xiaoshuai66.couponking.merchant.admin.service.impl
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/9 22:59
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl extends ServiceImpl<CouponTemplateMapper, CouponTemplateDO> implements CouponTemplateService {

    private final CouponTemplateMapper couponTemplateMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final MerchantAdminChainContext merchantAdminChainContext;

    private final CouponTemplateLogMapper couponTemplateLogMapper;

    @Override
    public void createCouponTemplate(CouponTemplateSaveReqDTO requestParam) {
        // 通过责任链验证请求参数是否正确
        merchantAdminChainContext.handler(MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY.name(), requestParam);

        // 新增优惠券模版信息到数据库
        CouponTemplateDO couponTemplateDO = BeanUtil.toBean(requestParam, CouponTemplateDO.class);
        couponTemplateDO.setStatus(CouponTemplateStatusEnum.ACTIVE.getStatus());
        couponTemplateDO.setShopNumber(UserContext.getShopNumber());
        couponTemplateMapper.insert(couponTemplateDO);

        // 缓存预热：通过将数据库的记录序列化成 JSON 字符串放入 Redis 缓存
        CouponTemplateQueryRespDTO actualRespDTO = BeanUtil.toBean(couponTemplateDO, CouponTemplateQueryRespDTO.class);
        Map<String, Object> cacheTargetMap = BeanUtil.beanToMap(actualRespDTO, false, true);
        Map<String, String> actualCacheTargetMap = cacheTargetMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() != null ? entry.getValue().toString() : ""
                ));
        String couponTemplateCacheKey = String.format(MerchantAdminRedisConstant.COUPON_TEMPLATE_KEY, couponTemplateDO.getId());

        // 通过 LUA 脚本执行设置 Hash 数据以及设置过期时间
        String luaScript = "redis.call('HMSET', KEYS[1], unpack(ARGV, 1, #ARGV - 1))" +
                "redis.call('EXPIREAT', KEYS[1], ARGV[#ARGV])";

        List<String> keys = Collections.singletonList(couponTemplateCacheKey);
        List<String> args = new ArrayList<>(actualCacheTargetMap.size() * 2 + 1);
        actualCacheTargetMap.forEach((key, value) -> {
            args.add(key);
            args.add(value);
        });

        // 优惠券活动过期时间转换位秒级别的 Unix 时间戳
        args.add(String.valueOf(couponTemplateDO.getValidStartTime().getTime() / 1000));

        // 执行 LUA 脚本
        stringRedisTemplate.execute(
                new DefaultRedisScript<>(luaScript, Long.class),
                keys,
                args.toArray()
        );

        try {
            String operationLog = String.format("%s 用户创建优惠券：%s，优惠对象：%s，优惠类型：%s，库存数量：%s，优惠商品编码：%s，有效期开始时间：%s，有效期结束时间：%s，领取规则：%s，消耗规则：%s;",
                    UserContext.getUserName(),
                    requestParam.getName(),
                    DiscountTargetEnum.findValueByType(requestParam.getTarget()),
                    DiscountTypeEnum.findValueByType(requestParam.getType()),
                    requestParam.getStock(),
                    requestParam.getGoods() == null ? "" : requestParam.getGoods(),
                    requestParam.getValidStartTime(),
                    requestParam.getValidEndTime(),
                    requestParam.getReceiveRule(),
                    requestParam.getConsumeRule());

            CouponTemplateLogDO couponTemplateLogDO = CouponTemplateLogDO.builder()
                    .couponTemplateId(String.valueOf(couponTemplateDO.getId()))
                    .operatorId(UserContext.getUserId())
                    .shopNumber(UserContext.getShopNumber())
                    .operationLog(operationLog)
                    .modifiedData(JSON.toJSONString(couponTemplateDO))
                    .build();
            couponTemplateLogMapper.insert(couponTemplateLogDO);
        } catch (Exception ex) {
            log.error("记录操作日志错误", ex);
            // 发起接口报警，但不能阻碍主流程继续执行
        }
    }
}
