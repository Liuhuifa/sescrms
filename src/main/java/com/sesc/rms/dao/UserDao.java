package com.sesc.rms.dao;

import com.sesc.rms.po.SysUserPo;

import java.util.List;

public interface UserDao {
    SysUserPo login(String uname);

    List<SysUserPo> listUser();

    int addUser(SysUserPo po);
}
