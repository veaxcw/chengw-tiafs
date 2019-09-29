package com.chengw.tiafs.services;

import com.alibaba.fastjson.JSON;
import com.chengw.tiafs.mapper.TeacherMapper;
import com.chengw.tiafs.model.po.TeacherBean;
import com.chengw.tiafs.model.vo.LoginEntity;
import com.chengw.tiafs.model.vo.TeacherEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthService {

    @Resource
    private TeacherMapper teacherMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public boolean check(LoginEntity userInfo){

        String o = redisTemplate.opsForValue().get("teacherInfo");
        List<TeacherEntity> teacherEntities = JSON.parseArray(o, TeacherEntity.class);

        if(teacherEntities == null || teacherEntities.size() == 0){
            List<TeacherBean> allTeacher = teacherMapper.getAllTeacher();
            if(allTeacher != null || allTeacher.size() != 0){
                List<TeacherEntity> collect = allTeacher.stream().map(b -> new TeacherEntity().from(b)).collect(Collectors.toList());
                String s = JSON.toJSONString(collect);
                redisTemplate.opsForValue().set("teacherInfo",s);
            }
        }
        for(TeacherEntity t:teacherEntities){
            boolean equals = userInfo.equals(t);
            if(!equals){
                continue;
            }else {
                return true;
            }
        }

        return false;
    }


}
