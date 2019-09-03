package com.sesc.rms.dao;

import com.sesc.rms.po.SysUserPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    SysUserPo login(String uname);

    List<SysUserPo> listUser();

    int addUser(SysUserPo po);

    SysUserPo listRoleAndPer(@Param("uname") String uname);

    /**
     * 查询指定角色下的用户
     * @param rid
     * @return
     */
    List<SysUserPo> listUserByRoleId(@Param("rid") Integer rid,
                                     @Param("uname") String uname);

    int del(@Param("uid")Integer uid);//删除用户
}
