package com.chengw.tiafs.services;

import com.chengw.tiafs.model.vo.SysUserVO;

/**
 * @author chengwei
 */
public interface SysUserService {

    /**
     * 查找用户
     * @param uid 用户id
     * @return 用户
     */
    SysUserVO findUserById(Long uid);
}
