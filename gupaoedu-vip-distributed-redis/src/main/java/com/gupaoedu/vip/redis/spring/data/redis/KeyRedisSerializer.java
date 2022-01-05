package com.gupaoedu.vip.redis.spring.data.redis;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.lang.Nullable;

/**
 * 重写redisTemplate默认的JDK序列化方式，并在xml配置
 */
public class KeyRedisSerializer extends JdkSerializationRedisSerializer {

    @Override
    public Object deserialize(@Nullable byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        } else {
            if(isStringType(bytes)){
                return new String(bytes);
            }
            try {
                return super.deserialize(bytes);
            } catch (Exception var3) {
                throw new SerializationException("Cannot deserialize", var3);
            }
        }
    }

    @Override
    public byte[] serialize(@Nullable Object object) {
        if (object == null) {
            return new byte[0];
        } else {
            if(object instanceof String){
                return ((String)object).getBytes();
            }
            try {
                return super.serialize(object);
            } catch (Exception var3) {
                throw new SerializationException("Cannot serialize", var3);
            }
        }
    }

    private boolean isStringType(@Nullable byte[] bytes) {
        // 不足6位，直接认为是字符串，,经测试单个字符序列化后的byte[]也有8位
        if (bytes.length < 6)
        {
            return true;
        }

        String protocol = Integer.toHexString(bytes[0] & 0x000000ff) + Integer.toHexString(bytes[1] & 0x000000ff);
        // 如果是jdk序列化后的
        if ("ACED".equals(protocol.toUpperCase()))
        {
            Object obj = super.deserialize(bytes);
            if (obj != null){
                return false;
            }else{// 如果是巧合，则返回的是null
                return true;
            }
        }
        return true;
    }

    private boolean isEmpty(@Nullable byte[] data) {
        return data == null || data.length == 0;
    }
}
