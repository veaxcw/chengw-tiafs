package com.chengw.tiafs.services;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分布式锁
 * @author veax
 */
@Service
@Slf4j
public class RedisLockService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public boolean lock(String targetId,String timestamp){
        if(redisTemplate.opsForValue().setIfAbsent(targetId,timestamp)){
            return true;
        }
        String currentLock = (String) redisTemplate.opsForValue().get(targetId);

        /**如果尚未超时，则获取上一个锁**/
        if(!Strings.isNullOrEmpty(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
            String preLock = (String) redisTemplate.opsForValue().getAndSet(targetId,timestamp);

            if(!Strings.isNullOrEmpty(preLock) && preLock.equals(currentLock)){
                return true;
            }
        }

        return false;
    }

    public void unlock(String  targetId){
        try {
            String currentLock = (String) redisTemplate.opsForValue().get(targetId);

            /**如果尚未超时，则获取上一个锁**/
            if(!Strings.isNullOrEmpty(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
                redisTemplate.delete(targetId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
