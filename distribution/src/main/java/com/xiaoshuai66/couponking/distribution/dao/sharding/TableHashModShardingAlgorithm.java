package com.xiaoshuai66.couponking.distribution.dao.sharding;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.List;

/**
 * ClassName: TableHashModShardingAlgorithm
 * Package: com.xiaoshuai66.couponking.distribution.dao.sharding
 * Description: 基于 HashMod 方式自定义分表算法
 *
 * @Author 赵帅
 * @Create 2025/2/11 15:12
 * @Version 1.0
 */
public final class TableHashModShardingAlgorithm implements StandardShardingAlgorithm<Long> {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        long id = preciseShardingValue.getValue();
        int shardingCount = collection.size();
        int mod = (int) hashShardingValue(id) % shardingCount;
        int index = 0;
        for (String targetName : collection) {
            if (index == mod) {
                return targetName;
            }
            index++;
        }
        throw new IllegalArgumentException("No target found for value: " + id);
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        // 暂无范围分片场景，默认返回空
        return List.of();
    }

    private long hashShardingValue(final Comparable<?> shardingValue) {
        return Math.abs((long) shardingValue.hashCode());
    }
}
