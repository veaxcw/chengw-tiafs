package com.chengw.tiafs.services.impl;

import com.chengw.tiafs.mapper.SysUserMapper;
import com.chengw.tiafs.model.vo.SysUserVO;
import com.chengw.tiafs.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author chengwei
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUserVO findUserById(Long uid) {
        return new SysUserVO().from(sysUserMapper.findUserById(uid));
    }
}
