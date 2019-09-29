package com.chengw.tiafs.services.support;

import com.chengw.tiafs.mapper.TeacherMapper;
import com.chengw.tiafs.model.po.TeacherBean;
import com.chengw.tiafs.services.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;


    public TeacherBean getTeacherByUsername(String username) {
        return teacherMapper.getTeacherByUsername(username);
    }

    public List<TeacherBean> getAllTeacher() {
        return teacherMapper.getAllTeacher();
    }


}
