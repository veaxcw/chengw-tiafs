package com.chengw.tiafs.controller;

import com.chengw.tiafs.services.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author chengw
 */
@Slf4j
@RestController()
public class SecController {

    @Resource
    private RedisLockService redisLockService;

    @RequestMapping(value = "/sec",produces = "text/html;charset = utf8")
    public String SecKill(){

        String targetId = UUID.randomUUID().toString();

        long TIMEOUT = 5 * 1000;
        long time = System.currentTimeMillis() + TIMEOUT;
        if(!redisLockService.lock(targetId,String.valueOf(time))){
            return "排队人数太多，请稍后再试.";
        }

        int surplusCount = 100;
        // 查询该商品库存，为0则活动结束
        if(surplusCount==0){
            return "活动结束.";
        }else {

            surplusCount--;
            try{
                //模拟减库存的处理时间
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

        //解锁
        redisLockService.unlock(targetId);

        return "恭喜您，秒杀成功。";
    }


}
