package com.chengw.tiafs.mapper;

import com.chengw.tiafs.model.po.SysUserBean;
import org.apache.ibatis.annotations.Param;

/**
 * @author chengwei
 */
public interface SysUserMapper {

    /**
     * 查找用户
     * @param uid 用户id
     * @return 用户
     */
    SysUserBean findUserById(@Param("uid") Long uid);

}
