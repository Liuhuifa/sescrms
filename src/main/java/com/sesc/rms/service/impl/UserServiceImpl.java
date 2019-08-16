package com.sesc.rms.service.impl;

import com.sesc.rms.dao.UserDao;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.service.inter.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao dao;
    @Override
    public SysUserPo login(String uname) {
        try {
            SysUserPo user = dao.login(uname);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
