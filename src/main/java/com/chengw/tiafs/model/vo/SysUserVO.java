package com.chengw.tiafs.model.vo;

import com.chengw.common.models.vo.GenericVO;
import com.chengw.tiafs.model.po.SysUserBean;
import lombok.Data;

/**
 * @author chengwei
 */
@Data
public class SysUserVO implements GenericVO<SysUserBean,SysUserVO> {

    private Long id;

    private String userName;

    private String name;

    private String email;

    private Long phone;

    private String password;

    private String sex;


    @Override
    public SysUserVO from(SysUserBean sysUserBean) {
        if (null == sysUserBean) {
            return null;
        }
        this.id = sysUserBean.getId();
        this.userName = sysUserBean.getUserName();
        this.name = sysUserBean.getName();
        this.email = sysUserBean.getName();
        this.sex = sysUserBean.getSex();
        return this;
    }

    @Override
    public SysUserBean to() {
        SysUserBean sysUserBean =  new SysUserBean();
        return sysUserBean;
    }
}
