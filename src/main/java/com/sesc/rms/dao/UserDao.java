package com.sesc.rms.dao;

import com.sesc.rms.po.SysUserPo;

public interface UserDao {
    SysUserPo login(String uname);
}
