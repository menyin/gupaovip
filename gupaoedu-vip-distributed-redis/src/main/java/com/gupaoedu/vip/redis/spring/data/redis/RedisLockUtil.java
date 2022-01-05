package com.gupaoedu.vip.redis.spring.data.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;

public class RedisLockUtil {
    private RedisTemplate redisTemplate;
    private DefaultRedisScript getLockRedisScript;
    private DefaultRedisScript releaseLockRedisScript;

    public RedisLockUtil() {
        ClassPathXmlApplicationContext ap = new ClassPathXmlApplicationContext("spring-redis-single.xml");
        redisTemplate = (RedisTemplate) ap.getBean("redisTemplate");
        getLockRedisScript = new DefaultRedisScript<String>();
        getLockRedisScript.setResultType(String.class);
        releaseLockRedisScript = new DefaultRedisScript<String>();
        releaseLockRedisScript.setResultType(String.class);

        //初始化装载 lua 脚本
        getLockRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("getLock.lua")));
        releaseLockRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("releaseLock.lua")));
    }

    /**
     * 加锁操作
     * @param key Redis 锁的 key 值
     * @param requestId 请求id，防止解了不该由自己解的锁 (随机生成)
     * @param expireTime 锁的超时时间(毫秒)
     * @param retryTimes 获取锁的重试次数
     * @return true or false
     */
    @SuppressWarnings("unchecked")
    public boolean lock(String key, String requestId, String expireTime, int retryTimes) {
        if (retryTimes <= 0)
            retryTimes = 1;

        try {
            int count = 0;
            while (true) {
                Object result = redisTemplate.execute(getLockRedisScript,  Collections.singletonList(key), requestId, expireTime);
                if ("1".equals(String.valueOf(result))) {
                    return true;
                } else {
                    count++;
                    if (retryTimes == count) {
//                        log.warn("has tried {} times , failed to acquire lock for key:{},requestId:{}", count, key, requestId);
                        return false;
                    } else {
//                        log.warn("try to acquire lock {} times for key:{},requestId:{}", count, key, requestId);
                        Thread.sleep(100);
                        continue;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解锁操作
     * @param key Redis 锁的 key 值
     * @param requestId 请求 id, 防止解了不该由自己解的锁  (随机生成)
     * @return true or false
     */
    @SuppressWarnings("unchecked")
    public boolean unLock(String key, String requestId) {
        Object result = redisTemplate.execute(releaseLockRedisScript,Collections.singletonList(key),requestId);
        if ("1".equals(String.valueOf(result))) {
            return true;
        }
        return false;
    }
}
