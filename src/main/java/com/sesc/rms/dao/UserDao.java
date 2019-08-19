package com.sesc.rms.dao;

import com.sesc.rms.po.SysUserPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    SysUserPo login(String uname);

    List<SysUserPo> listUser();

    int addUser(SysUserPo po);

    SysUserPo listRoleAndPer(@Param("uname") String uname);
}
