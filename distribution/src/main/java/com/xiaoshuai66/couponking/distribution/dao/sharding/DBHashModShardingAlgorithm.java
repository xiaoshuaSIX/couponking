package com.xiaoshuai66.couponking.distribution.dao.sharding;

import lombok.Getter;
import org.apache.shardingsphere.infra.util.exception.ShardingSpherePreconditions;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.exception.algorithm.sharding.ShardingAlgorithmInitializationException;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * ClassName: DBHashModShardingAlgorithm
 * Package: com.xiaoshuai66.couponking.distribution.dao.sharding
 * Description: 基于 HashMod 方式自定义分表算法
 *
 * @Author 赵帅
 * @Create 2025/2/11 15:12
 * @Version 1.0
 */
public class DBHashModShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    @Getter
    private Properties props;

    private int shardingCount;
    private static final String SHARDING_COUNT_KEY = "sharding-count";

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        int mod = getShardingMod(preciseShardingValue.getValue(), collection.size());
        int index = 0;
        // 通过刚才的数据库下表，获取到数据库逻辑名称 ds_0 或者 ds_1
        for (String targetName : collection) {
            if (index == mod) {
                return targetName;
            }
            index++;
        }
        throw new IllegalArgumentException("No target fount for value: " + preciseShardingValue.getValue());
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        return List.of();
    }

    /**
     * 初始化配置对象
     *
     * @param props 配置文件的内容映射的对象
     */
    @Override
    public void init(Properties props) {
        this.props = props;
        shardingCount = getShardingCount(props);
    }

    public int getShardingMod(long id, int availableTargetSize) {
        return (int) hashShardingValue(id) % shardingCount / (shardingCount / availableTargetSize);
    }

    private int getShardingCount(final Properties props) {
        ShardingSpherePreconditions.checkState(props.containsKey(SHARDING_COUNT_KEY), () -> new ShardingAlgorithmInitializationException(getType(), "Sharding count cannot be null."));
        return Integer.parseInt(props.getProperty(SHARDING_COUNT_KEY));
    }

    private long hashShardingValue(final Comparable<?> shardingValue) {
        return Math.abs((long) shardingValue.hashCode());
    }
}
