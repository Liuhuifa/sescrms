package com.sesc.rms.service.inter;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.util.Result;

import java.util.List;


public interface UserService {
    SysUserPo login(String uname);

    PageInfo<SysUserPo> listUser(Integer pageindex, Integer pagesize);

    Result addUser(SysUserPo po,Integer [] rids);

    SysUserPo listRoleAndPer(String uname);

    Result listUserByRoleId(Integer rid,String uname,Integer pageindex) throws Exception;

    Result del(Integer uid);

    /**
     * 个人信息
     * @param uid
     * @return
     */
    SysUserPo findByUid(Integer uid);

    /**
     * 修改用户信息
     * @param po
     * @return
     */
    Result modifyByUser(SysUserPo po);

}
