package com.chengw.tiafs.redis;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * @author chengw
 */
@Slf4j
@Data
@Component("lock")
public class DistributedLock {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private static final String LOCKED = "LOCKED";
    private static final long TIME_OUT = 1000;

    /**
    *默认锁有效时间
    * **/
    public static final long EXPIRE = 60;

    private String lockKey;
    private long lockExpire = EXPIRE;
    private long timeOut = TIME_OUT;

    public DistributedLock() {
    }

    public DistributedLock(String lockName, long lockExpire, long timeout) {
        this.lockKey = "lock:" + lockName;
        this.lockExpire = lockExpire;
        this.timeOut = timeout;
    }

    /**
     * 加锁
     * @return
     */
    public  Boolean lockWithTimeOut(){
            String identifier = UUID.randomUUID().toString();
            long end = System.currentTimeMillis() + timeOut;
            while (System.currentTimeMillis() < end){
                if(redisTemplate.opsForValue().setIfAbsent(lockKey,identifier)){
                    System.out.println("成功请求锁");
                    redisTemplate.expire(lockKey,lockExpire, TimeUnit.SECONDS);
                    return true;
                }

                //获取锁失败后，在延时后进行重试
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("请求锁超时");
            return false;
    }


    public Boolean isLock(){
        return redisTemplate.hasKey(lockKey);
    }

    public void unlock(){
        if (isLock()) {
            log.info("释放锁");
            redisTemplate.delete(lockKey);
        }
    }

}
