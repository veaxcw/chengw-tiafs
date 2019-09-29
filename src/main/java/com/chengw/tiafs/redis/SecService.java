package com.chengw.tiafs.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 秒杀系统模拟
 * @author chengw
 */
@Component
public class SecService {

    @Autowired
    private DistributedLock lock;


    public void Sec(int i){
        lock.setLockKey(String.valueOf(i));
        lock.setLockExpire(120);
        lock.setTimeOut(5000);
        try {
            if(lock.lockWithTimeOut()){
                System.out.println("执行业务代码");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
