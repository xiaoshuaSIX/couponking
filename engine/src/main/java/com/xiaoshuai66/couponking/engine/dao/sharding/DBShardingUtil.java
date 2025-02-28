package com.xiaoshuai66.couponking.engine.dao.sharding;

import cn.hutool.core.lang.Singleton;

import java.util.Collection;
import java.util.List;

/**
 * @className: DBShardingUtil
 * @Description: 针对项目中 IN 操作跨数据库场景进行拆分数据源
 * @author: zhaoshuai
 * @date: 2025/2/28 17:37
 */
public final class DBShardingUtil {
    /**
     * 获取数据库分片算法类，在该类初始化时向 Singleton 放入实例
     */
    private static final DBHashModShardingAlgorithm dbShardingAlgorithm = Singleton.get(DBHashModShardingAlgorithm.class);

    /**
     * 解决查询商家优惠券 IN 场景跨库表不存在问题
     *
     * @param shopNumber 分片键 shopNumber
     * @return 返回 shopNumber 所在的数据源
     */
    public static int doCouponSharding(Long shopNumber) {
        return dbShardingAlgorithm.getShardingMod(shopNumber, getAvailableDatabases().size());
    }

    /**
     * 获取可用的数据源列表
     */
    private static Collection<String> getAvailableDatabases() {
        return List.of("ds0", "ds1");
    }
}
