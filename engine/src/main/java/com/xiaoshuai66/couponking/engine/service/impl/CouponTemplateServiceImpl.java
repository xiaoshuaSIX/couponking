package com.xiaoshuai66.couponking.engine.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai66.couponking.engine.common.constant.EngineRedisConstant;
import com.xiaoshuai66.couponking.engine.common.enums.CouponTemplateStatusEnum;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.engine.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.engine.dao.sharding.DBShardingUtil;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateQueryReqDTO;
import com.xiaoshuai66.couponking.engine.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.engine.service.CouponTemplateService;
import com.xiaoshuai66.couponking.framework.exception.ClientException;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName CouponTemplateServiceImpl.java
 * @Description 优惠券模板业务逻辑实现层
 * @author zhaoshuai
 * @createTime 2025-02-17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl extends ServiceImpl<CouponTemplateMapper, CouponTemplateDO> implements CouponTemplateService {

    private final CouponTemplateMapper couponTemplateMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;
    private final RBloomFilter<String> couponTemplateQueryBloomFilter;

    @Override
    public CouponTemplateQueryRespDTO findCouponTemplate(CouponTemplateQueryReqDTO requestParam) {
        // 查询 Redis 缓存中是否存在优惠券模板信息
        String couponTemplateCacheKey = String.format(EngineRedisConstant.COUPON_TEMPLATE_KEY, requestParam.getCouponTemplateId());
        Map<Object, Object> couponTemplateCacheMap = stringRedisTemplate.opsForHash().entries(couponTemplateCacheKey);

        // 如果存在直接返回，不存在需要通过双重判定锁的形式读取数据库中的记录
        // 缓存中不存在，查询布隆过滤器：不存在 返回，可能存在。查询Redis中是否有空值信息：有空值 不存在，没有空值。抢锁：查询数据库
        if (MapUtil.isEmpty(couponTemplateCacheMap)) {
            // 判断布隆过滤器是否存在指定模板 ID，不存在直接返回错误
            if (!couponTemplateQueryBloomFilter.contains(requestParam.getCouponTemplateId())) {
                throw new ClientException("优惠券模版不存在");
            }

            // 查询 Redis 缓存中是否存在优惠券模板空值信息，如果有代表模板不存在，直接返回
            String couponTemplateIsNullCacheKey = String.format(EngineRedisConstant.COUPON_TEMPLATE_IS_NULL_KEY, requestParam.getCouponTemplateId());
            Boolean hasKeyFlag = stringRedisTemplate.hasKey(couponTemplateIsNullCacheKey);
            if (hasKeyFlag) {
                // 有空值信息
                throw new ClientException("优惠券模版不存在");
            }

            // 获取优惠券模板分布式锁
            RLock lock = redissonClient.getLock(String.format(EngineRedisConstant.LOCK_COUPON_TEMPLATE_KEY, requestParam.getCouponTemplateId()));
            lock.lock();

            try {
                // 双重判定空值缓存是否存在，存在则继续抛异常
                hasKeyFlag = stringRedisTemplate.hasKey(couponTemplateIsNullCacheKey);
                if (hasKeyFlag) {
                    // 有空值信息
                    throw new ClientException("优惠券模版不存在");
                }

                // 通过双重判定优化大量请求无意义查询数据库
                couponTemplateCacheMap = stringRedisTemplate.opsForHash().entries(couponTemplateCacheKey);
                if (MapUtil.isEmpty(couponTemplateCacheMap)) {
                    LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                        .eq(CouponTemplateDO::getShopNumber, Long.parseLong(requestParam.getShopNumber()))
                        .eq(CouponTemplateDO::getId, Long.parseLong(requestParam.getCouponTemplateId()))
                        .eq(CouponTemplateDO::getStatus, CouponTemplateStatusEnum.ACTIVE.getStatus());
                    CouponTemplateDO couponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);

                    // 优惠券模板不存在或者已过期直接抛出异常
                    if (couponTemplateDO == null) {
                        throw new ClientException("优惠券模板不存在或已过期");
                    }

                    // 通过将数据库的记录序列化成 JSON 字符串放入 Redis 缓存
                    CouponTemplateQueryRespDTO actualRespDTO = BeanUtil.toBean(couponTemplateDO, CouponTemplateQueryRespDTO.class);
                    Map<String, Object> cacheTargetMap = BeanUtil.beanToMap(actualRespDTO, false, true);
                    Map<String, String> actualCacheTargetMap = cacheTargetMap.entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    entry -> entry.getValue() != null ? entry.getValue().toString() : ""
                            ));

                    // 通过 LUA 脚本执行设置 Hash 数据以及设置过期时间
                    String luaScript = "redis.call('HMSET', KEYS[1], unpack(ARGV, 1, #ARGV - 1))" +
                            "redis.call('EXPIREAT', KEYS[1], ARGV[#ARGV])";

                    List<String> keys = Collections.singletonList(couponTemplateCacheKey);
                    ArrayList<Object> args = new ArrayList<>(actualCacheTargetMap.size() * 2 + 1);
                    actualCacheTargetMap.forEach((key, value) -> {
                        args.add(key);
                        args.add(value);
                    });

                    // 优惠券活动过期时间转换为秒级别的 Unix 时间戳
                    args.add(String.valueOf(couponTemplateDO.getValidEndTime().getTime() / 1000));

                    // 执行 LUA 脚本
                    stringRedisTemplate.execute(
                            new DefaultRedisScript<>(luaScript, Long.class),
                            keys,
                            args.toArray()
                    );
                    couponTemplateCacheMap =cacheTargetMap.entrySet()
                            .stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                }

            } finally {
                lock.unlock();
            }
        }

        return BeanUtil.mapToBean(couponTemplateCacheMap, CouponTemplateQueryRespDTO.class, false, CopyOptions.create());
    }

    @Override
    public List<CouponTemplateDO> listCouponTemplateByIds(List<Long> couponTemplateIds, List<Long> shopNumbers) {
        // 1.将 shopNumber集合 对应的index拆分到数据库中
        Map<Integer, List<Long>> databaseIndexMap = splitIndexByDatabase(shopNumbers);

        ArrayList<CouponTemplateDO> result = new ArrayList<>();
        // 2.对每个数据库执行查询
        for (Map.Entry<Integer, List<Long>> entry : databaseIndexMap.entrySet()) {
            List<Long> shopNumbersSubset = entry.getValue();

            // 执行查询
            List<CouponTemplateDO> couponTemplateDOList = queryDatabase(couponTemplateIds, shopNumbersSubset);
            result.addAll(couponTemplateDOList);
        }

        return result;
    }

    private List<CouponTemplateDO> queryDatabase(List<Long> couponTemplateIds, List<Long> shopNumbers) {
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .in(CouponTemplateDO::getShopNumber, shopNumbers)
                .in(CouponTemplateDO::getId, couponTemplateIds);
        return couponTemplateMapper.selectList(queryWrapper);
    }

    private Map<Integer, List<Long>> splitIndexByDatabase(List<Long> shopNumbers) {
        Map<Integer, List<Long>> databaseShopNumberMap = new HashMap<>();

        for (Long shopNumber : shopNumbers) {
            int databaseMod = DBShardingUtil.doCouponSharding(shopNumber);
            databaseShopNumberMap
                    .computeIfAbsent(databaseMod, k -> new ArrayList<>())
                    .add(shopNumber);
        }

        return databaseShopNumberMap;
    }
}
