package com.chengw.tiafs.model.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chengw
 */
@Getter
@Setter
@ToString
public class LoginEntity {

    private String userCode;

    private String username;

    private String password;

    private String checkCode;

    public boolean equals(LoginEntity obj) {

        if(obj != null){
            return this.username.equals(obj.getUsername()) && this.password.equals(obj.password);
        }
        return false;
    }
}
