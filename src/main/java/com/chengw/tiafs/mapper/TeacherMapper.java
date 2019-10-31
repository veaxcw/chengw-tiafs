package com.chengw.tiafs.mapper;

import com.chengw.tiafs.model.po.TeacherBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author veax
 */
public interface TeacherMapper {


    /**
     * 根据用户代码返回用户信息
     * @param userCode
     * @return
     */
    TeacherBean getTeacherByUserCode(String userCode);

    /**
     * 根据用户名查找
     * @param userName
     * @return
     */
    TeacherBean getTeacherByUsername(@Param("userName") String userName);

    /**
     * 返回所有老师
     * @return 返回所有老师
     */
    List<TeacherBean> getAllTeacher();



}
