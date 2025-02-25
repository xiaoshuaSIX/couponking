package com.xiaoshuai66.couponking.framework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @Description: Redis Key 序列化 | 定义全局统一前缀
 * @author 赵帅
 * @Create 2025/2/26 0:41
 */
@RequiredArgsConstructor
public class RedisKeySerializer implements InitializingBean, RedisSerializer<String> {

    private final String keyPrefix;

    private final String charsetName;

    private Charset charset;

    /**
     * 在所有 Bean 属性设置完成后自动调用。
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        charset = Charset.forName(charsetName);
    }

    /**
     * 该方法将传入的字符串键 key 加上 keyPrefix，然后将结果字符串转换为字节数组。这样在 Redis 中存储时，键会带有特定的前缀
     */
    @Override
    public byte[] serialize(String key) throws SerializationException {
        String builderKey = keyPrefix + key;
        return builderKey.getBytes();
    }

    /**
     * 该方法将字节数组 bytes 转换回字符串，使用之前设置的 charset 进行字符编码。
     */
    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return new String(bytes, charset);
    }
}
