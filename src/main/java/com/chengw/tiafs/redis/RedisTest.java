package com.chengw.tiafs.redis;

import com.chengw.tiafs.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis 测试
 * @author chengw
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("xveacw8023");
        int i = 0;
        try {
            long start = System.currentTimeMillis();

            while (true){
                long end = System.currentTimeMillis();
                if((end - start) > 1000){
                    break;
                }
                i++;
                jedis.set("test"+i,i+"");

            }
        } finally {
            jedis.close();
        }
        System.out.println("redis 一秒内操作："+ i + "次");
    }

    @Test
    public void Test() {
        Role role = new Role();
        role.setId(1L);
        role.setName("chengw");

        //redisTemplate.opsForValue().set("chengw",role);

        SessionCallback<Role> sessionCallback = new SessionCallback<Role>() {
            @Override
            public <K, V> Role execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                redisOperations.boundValueOps((K) "chengw").set((V) role);
                return null;
            }
        };
        redisTemplate.execute(sessionCallback);
    }



    @Test
    public void redis() {

        Map a = new HashMap();

        Article article = new Article();
        article.setAId(1);
        article.setAName("redis");
        article.setAPath("F:/");
        article.setPublishDate(new Date().toString());
        article.setPublishReader("chengw");
        article.setUploadDate("today");

        a.put("article",article);
        redisUtil.hset("article","AId",article.getAId());
        redisUtil.hset("article","AName",article.getAName());
        redisUtil.hset("article","APath",article.getAPath());
        redisUtil.hset("article","PublishDate",article.getPublishDate());
        redisUtil.hset("article","PublishReader",article.getPublishReader());
        redisUtil.hset("article","uploadDate",article.getUploadDate());





        //redisUtil.hmset("article",a);
        //redisUtil.hdel("article",fields);
        // redisUtil.del("article");


    }

}

