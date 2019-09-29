package com.chengw.tiafs.services;


import com.chengw.tiafs.model.po.TeacherBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
    TeacherBean getTeacherByUsername(@Param("username") String username);
    List<TeacherBean> getAllTeacher();


}
