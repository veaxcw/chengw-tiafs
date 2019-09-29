package com.chengw.tiafs.model.vo;

import com.chengw.tiafs.model.po.TeacherBean;
import lombok.Data;

/**
 * @author veax
 */
@Data
public class TeacherEntity {

    private int teacherId;

    private String userCode;

    private String userName;

    private String password;

    public TeacherEntity from(TeacherBean b){
        this.teacherId = b.getTeacherId();
        this.userCode = b.getUserCode();
        this.userName = b.getUserName();
        this.password = b.getPassword();

        return this;
    }
}
