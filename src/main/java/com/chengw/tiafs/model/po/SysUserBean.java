package com.chengw.tiafs.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @author chengwei
 */
@Data
public class SysUserBean  {

    private Long id;

    private String userName;

    private String name;

    private String email;

    private Long phone;

    private String password;

    private String sex;

    private Date createdDate;

    private Date updatedDate;

}
