package com.xiaoshuai66.couponking.merchant.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import com.xiaoshuai66.couponking.framework.exception.ClientException;
import com.xiaoshuai66.couponking.framework.exception.ServiceException;
import com.xiaoshuai66.couponking.merchant.admin.common.constant.MerchantAdminRedisConstant;
import com.xiaoshuai66.couponking.merchant.admin.common.context.UserContext;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.merchant.admin.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateNumberReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplatePageQueryReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.resp.CouponTemplatePageQueryRespDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.merchant.admin.common.enums.CouponTemplateStatusEnum;
import com.xiaoshuai66.couponking.merchant.admin.mq.event.CouponTemplateDelayEvent;
import com.xiaoshuai66.couponking.merchant.admin.mq.producer.CouponTemplateDelayExecuteStatusProducer;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTemplateService;
import com.xiaoshuai66.couponking.merchant.admin.service.basics.chain.MerchantAdminChainContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.xiaoshuai66.couponking.merchant.admin.common.enums.ChainBizMarkEnum.MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY;

/**
 * ClassName: CouponTemplateServiceImpl
 * Package: com.xiaoshuai66.couponking.merchant.admin.service.impl
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/9 22:59
 * @Version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl extends ServiceImpl<CouponTemplateMapper, CouponTemplateDO> implements CouponTemplateService {

    private final CouponTemplateMapper couponTemplateMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final MerchantAdminChainContext merchantAdminChainContext;
    private final CouponTemplateDelayExecuteStatusProducer couponTemplateDelayExecuteStatusProducer;

    @LogRecord(
            success = """
                    创建优惠券：{{#requestParam.name}}， \
                    优惠对象：{COMMON_ENUM_PARSE{'DiscountTargetEnum' + '_' + #requestParam.target}}， \
                    优惠类型：{COMMON_ENUM_PARSE{'DiscountTypeEnum' + '_' + #requestParam.type}}， \
                    库存数量：{{#requestParam.stock}}， \
                    优惠商品编码：{{#requestParam.goods}}， \
                    有效期开始时间：{{#requestParam.validStartTime}}， \
                    有效期结束时间：{{#requestParam.validEndTime}}， \
                    领取规则：{{#requestParam.receiveRule}}， \
                    消耗规则：{{#requestParam.consumeRule}};
                    """,
            type = "CouponTemplate",
            bizNo = "{{#bizNo}}",
            extra = "{{#requestParam.toString()}}"
    )
    @Override
    public void createCouponTemplate(CouponTemplateSaveReqDTO requestParam) {
        // 业务中睡一会，避免执行太快，模拟业务繁忙
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 通过责任链验证请求参数是否正确
        merchantAdminChainContext.handler(MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY.name(), requestParam);

        // 新增优惠券模版信息到数据库
        CouponTemplateDO couponTemplateDO = BeanUtil.toBean(requestParam, CouponTemplateDO.class);
        couponTemplateDO.setStatus(CouponTemplateStatusEnum.ACTIVE.getStatus());
        couponTemplateDO.setShopNumber(UserContext.getShopNumber());
        couponTemplateMapper.insert(couponTemplateDO);

        //因为模板 ID 是运行中生成的，@LogRecord 默认拿不到，所以我们需要手动设置
        LogRecordContext.putVariable("bizNo", couponTemplateDO.getId());

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

        // 发送延时消息事件，优惠券活动到期修改优惠券模版状态
        CouponTemplateDelayEvent couponTemplateDelayEvent = CouponTemplateDelayEvent.builder()
                .shopNumber(UserContext.getShopNumber())
                .couponTemplateId(couponTemplateDO.getId())
                .delayTime(couponTemplateDO.getValidEndTime().getTime())
                .build();
        couponTemplateDelayExecuteStatusProducer.sendMessage(couponTemplateDelayEvent);
    }

    @Override
    public IPage<CouponTemplatePageQueryRespDTO> pageQueryCouponTemplate(CouponTemplatePageQueryReqDTO requestParam) {
        // 构建分页查询模板
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getShopNumber, UserContext.getShopNumber())
                .like(StrUtil.isNotBlank(requestParam.getName()), CouponTemplateDO::getName, requestParam.getName())
                .like(StrUtil.isNotBlank(requestParam.getGoods()), CouponTemplateDO::getGoods, requestParam.getGoods())
                .eq(Objects.nonNull(requestParam.getType()), CouponTemplateDO::getType, requestParam.getType())
                .eq(Objects.nonNull(requestParam.getTarget()), CouponTemplateDO::getTarget, requestParam.getTarget());

        // Mybatis-Plus 分页查询优惠券模板信息
        IPage<CouponTemplateDO> selectPage = couponTemplateMapper.selectPage(requestParam, queryWrapper);

        // 转换数据库持久层对象为优惠券模板返回参数
        return selectPage.convert(each -> BeanUtil.toBean(each, CouponTemplatePageQueryRespDTO.class));
    }

    @Override
    public CouponTemplateQueryRespDTO findCouponTemplateById(String couponTemplateId) {
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getShopNumber, UserContext.getShopNumber())
                .eq(CouponTemplateDO::getId, couponTemplateId);

        CouponTemplateDO couponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);
        return BeanUtil.toBean(couponTemplateDO, CouponTemplateQueryRespDTO.class);
    }

    @LogRecord(
            success = "增加发行量：{{#requestParam.number}}",
            type = "CouponTemplate",
            bizNo = "{{#requestParam.couponTemplateId}}"
    )
    @Override
    public void increaseNumberCouponTemplate(CouponTemplateNumberReqDTO requestParam) {
        // 验证是否存在数据横向越权
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getShopNumber, UserContext.getShopNumber())
                .eq(CouponTemplateDO::getId, requestParam.getCouponTemplateId());
        CouponTemplateDO couponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);
        if (couponTemplateDO == null) {
            // 一旦查询优惠券不存在，基本可判定横向越权，可上报该异常行为，次数多了后执行封号等处理
            throw new ClientException("优惠券模板异常，请检查操作是否正确...");
        }

        // 验证优惠券模板是否正确
        if (ObjectUtil.notEqual(couponTemplateDO.getStatus(), CouponTemplateStatusEnum.ACTIVE.getStatus())) {
            throw new ClientException("优惠券模板已结束");
        }

        // 记录优惠券模板修改前数据
        LogRecordContext.putVariable("originalData", JSON.toJSONString(couponTemplateDO));

        // 设置数据库优惠模板增加库存发行量
        int increased = couponTemplateMapper.increaseNumberCouponTemplate(UserContext.getShopNumber(), requestParam.getCouponTemplateId(), requestParam.getNumber());
        //SqlHelper.retBool() 工具方法，用于判断数据库操作是否成功
        if (!SqlHelper.retBool(increased)) {
            throw new ServiceException("优惠券模板增加发行量失败");
        }

        // 增加优惠券模板缓存库存发行量
        String couponTemplateCacheKey = String.format(MerchantAdminRedisConstant.COUPON_TEMPLATE_KEY, couponTemplateDO.getId());
        stringRedisTemplate.opsForHash().increment(couponTemplateCacheKey, "stock", requestParam.getNumber());
    }

    @LogRecord(
            success = "结束优惠券",
            type = "CouponTemplate",
            bizNo = "{{#couponTemplateId}}"
    )
    @Override
    public void terminateCouponTemplate(String couponTemplateId) {
        // 验证是否存在数据横向越权
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getShopNumber, UserContext.getShopNumber())
                .eq(CouponTemplateDO::getId, couponTemplateId);
        CouponTemplateDO couponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);
        if (couponTemplateDO == null) {
            // 一旦查询优惠券不存在，基本可判定横向越权，可上报该异常行为，次数多了后执行封号等处理
            throw new ClientException("优惠券模板异常，请检查操作是否正确...");
        }

        // 验证优惠券模板是否正常
        // 验证优惠券模板是否正常
        if (ObjectUtil.notEqual(couponTemplateDO.getStatus(), CouponTemplateStatusEnum.ACTIVE.getStatus())) {
            throw new ClientException("优惠券模板已结束");
        }

        // 记录优惠券模板修改前数据
        LogRecordContext.putVariable("originalData", JSON.toJSONString(couponTemplateDO));

        // 修改优惠券模板为结束状态
        CouponTemplateDO updateCouponTemplateDO = CouponTemplateDO.builder()
                .status(CouponTemplateStatusEnum.ENDED.getStatus())
                .build();
        Wrapper<CouponTemplateDO> updateWrapper = Wrappers.lambdaUpdate(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getId, couponTemplateDO.getId())
                .eq(CouponTemplateDO::getShopNumber, couponTemplateDO.getShopNumber());
        couponTemplateMapper.update(updateCouponTemplateDO, updateWrapper);

        // 修改优惠券模板缓存状态为结束状态
        String couponTemplateCacheKey = String.format(MerchantAdminRedisConstant.COUPON_TEMPLATE_KEY, couponTemplateId);
        stringRedisTemplate.opsForHash().put(couponTemplateCacheKey, "status", String.valueOf(CouponTemplateStatusEnum.ENDED.getStatus()));
    }
}
