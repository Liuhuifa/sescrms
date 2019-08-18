package com.sesc.rms.service.inter;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.util.Result;

import java.util.List;


public interface UserService {
    SysUserPo login(String uname);

    PageInfo<SysUserPo> listUser(Integer pageindex, Integer pagesize);

    int addUser(SysUserPo po);
}
