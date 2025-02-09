package com.xiaoshuai66.couponking.merchant.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.xiaoshuai66.couponking.framework.exception.ClientException;
import com.xiaoshuai66.couponking.merchant.admin.constant.MerchantAdminRedisConstant;
import com.xiaoshuai66.couponking.merchant.admin.context.UserContext;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.merchant.admin.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.merchant.admin.enums.CouponTemplateStatusEnum;
import com.xiaoshuai66.couponking.merchant.admin.enums.DiscountTargetEnum;
import com.xiaoshuai66.couponking.merchant.admin.enums.DiscountTypeEnum;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTemplateService;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

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
public class CouponTemplateServiceImpl implements CouponTemplateService {

    private final CouponTemplateMapper couponTemplateMapper;
    private final StringRedisTemplate stringRedisTemplate;

    private final int maxStock = 20000000;

    @Override
    public void createCouponTemplate(CouponTemplateSaveReqDTO requestParam) {
        // 验证必填参数是否为空或空的字符串
        if (StrUtil.isEmpty(requestParam.getName())) {
            throw new ClientException("优惠券名称不能为空");
        }
        if (ObjectUtil.isEmpty(requestParam.getSource())) {
            throw new ClientException("优惠券来源不能为空");
        }
        if (ObjectUtil.isEmpty(requestParam.getTarget())) {
            throw new ClientException("优惠对象不能为空");
        }
        if (ObjectUtil.isEmpty(requestParam.getType())) {
            throw new ClientException("优惠类型不能为空");
        }
        if (ObjectUtil.isEmpty(requestParam.getValidStartTime())) {
            throw new ClientException("有效期开始时间不能为空");
        }
        if (ObjectUtil.isEmpty(requestParam.getValidEndTime())) {
            throw new ClientException("有效期结束时间不能为空");
        }
        if (ObjectUtil.isEmpty(requestParam.getStock())) {
            throw new ClientException("库存不能为空");
        }
        if (StringUtil.isEmpty(requestParam.getReceiveRule())) {
            throw new ClientException("领取规则不能为空");
        }
        if (StringUtil.isEmpty(requestParam.getConsumeRule())) {
            throw new ClientException("消耗规则不能为空");
        }

        // 验证参数基本数据关系是否正确
        boolean targetAnyMatch = Arrays.stream(DiscountTargetEnum.values())
                .anyMatch(enumConstant -> enumConstant.getType() == requestParam.getTarget());
        if (!targetAnyMatch) {
            // 此处已经基本能判断数据请求属于恶意攻击，可以上报风控中心进行封禁账号
            throw  new ClientException("优惠对象值不存在");
        }
        if (ObjectUtil.equal(requestParam.getTarget(), DiscountTargetEnum.ALL_STORE_GENERAL)
                && StrUtil.isNotEmpty(requestParam.getGoods())) {
            throw new ClientException("优惠券全店通用不可设置指定商品");
        }
        if (ObjectUtil.equal(requestParam.getTarget(), DiscountTargetEnum.PRODUCT_SPECIFIC)
                && StrUtil.isEmpty(requestParam.getGoods())) {
            throw new ClientException("优惠券商品专属未设置指定商品");
        }

        boolean typeAnyMatch = Arrays.stream(DiscountTypeEnum.values())
                .anyMatch(enumConstant -> enumConstant.getType() == requestParam.getType());
        if (!typeAnyMatch) {
            // 此处已经基本能判断数据请求属于恶意攻击，可以上报风控中心进行封禁账号
            throw  new ClientException("优惠类型不存在");
        }

        Date now = new Date();
        if (requestParam.getValidStartTime().before(now)) {
            // 为了方便测试，不用关注这个事件，这里取消异常抛出
            // throw new ClientException("有效开始时间不能早于当前时间");
        }

        if (requestParam.getStock() <= 0 || requestParam.getStock() > maxStock) {
            // 此处已经基本能判断数据请求属于恶意攻击，可以上报风控中心进行封禁账号
            throw  new ClientException("库存数量设置异常");
        }

        if (!JSON.isValid(requestParam.getReceiveRule())) {
            // 此处已经基本能判断数据请求属于恶意攻击，可以上报风控中心进行封禁账号
            throw  new ClientException("领取规则格式错误");
        }

        if (!JSON.isValid(requestParam.getConsumeRule())) {
            // 此处已经基本能判断数据请求属于恶意攻击，可以上报风控中心进行封禁账号
            throw  new ClientException("消耗规则格式错误");
        }

        // 验证参数数据是否正确
        if (ObjectUtil.equal(requestParam.getTarget(), DiscountTargetEnum.PRODUCT_SPECIFIC)) {
            // 调用商品中台验证商品是否存在，如果不存在抛出异常
            // ......
        }

        // 新增优惠券模版信息到数据库
        CouponTemplateDO couponTemplateDO = BeanUtil.toBean(requestParam, CouponTemplateDO.class);
        couponTemplateDO.setStatus(CouponTemplateStatusEnum.ACTIVE.getStatus());
        couponTemplateDO.setShopNumber(UserContext.getShopNumber());
        couponTemplateMapper.insert(couponTemplateDO);

        // 缓存预热：通过将数据库的记录序列化成 JSON 字符串放入 Redis 缓存
        CouponTemplateQueryRespDTO actualRespDTO = BeanUtil.toBean(couponTemplateDO, CouponTemplateQueryRespDTO.class);
        Map<String, Object> cacheTargetMap = BeanUtil.beanToMap(actualRespDTO, false, true);
        Map<String, Object> actualCacheTargetMap = cacheTargetMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() != null ? entry.getValue() : ""
                ));
        String couponTemplateCacheKey = String.format(MerchantAdminRedisConstant.COUPON_TEMPLATE_KEY, couponTemplateDO.getId());
        stringRedisTemplate.opsForHash().putAll(couponTemplateCacheKey, actualCacheTargetMap);
    }
}
