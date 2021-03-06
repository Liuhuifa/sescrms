package com.sesc.rms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.dao.SysUserRoleMapper;
import com.sesc.rms.dao.UserDao;
import com.sesc.rms.po.SysUserPo;
import com.sesc.rms.po.SysUserRolePo;
import com.sesc.rms.service.inter.UserService;
import com.sesc.rms.util.MD5Util;
import com.sesc.rms.util.Result;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao dao;

    @Resource
    private SysUserRoleMapper mapper;

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

    @Override
    public PageInfo<SysUserPo> listUser(Integer pageindex, Integer pagesize) {
        try {
            PageInfo<SysUserPo> infos = null;
            if (pageindex!=null && pagesize!=null){
                PageHelper.startPage(pageindex,pagesize);
                List<SysUserPo> sysUserPos = dao.listUser();
                infos = new PageInfo<>(sysUserPos);
                return infos;
            }else{
                List<SysUserPo> sysUserPos = dao.listUser();
                infos = new PageInfo<>(sysUserPos);
                return infos;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Result addUser(SysUserPo po,Integer [] rids) {

        Date date = new Date();
        Long time = date.getTime();
        po.setSalt(time.toString());
        ByteSource bytes = ByteSource.Util.bytes(time.toString());
        SimpleHash md5 = new SimpleHash("MD5", po.getPassword(), bytes, 99);
        po.setPassword(md5.toString());
        int result = dao.addUser(po);

        if (result>0){
            List<SysUserRolePo> list = new ArrayList<>();
            for (int i=0; i<rids.length; i++){
                SysUserRolePo rpo =new SysUserRolePo();
                rpo.setSysUserId(po.getUid());
                rpo.setSysRoleId(rids[i]);
                list.add(rpo);
            }
            int ur = mapper.addAny(list);
            if (ur>0){
                return Result.success();
            }
        }
        return Result.fail("添加失败");
    }

    @Override
    public SysUserPo listRoleAndPer(String uname) {

        try {
            SysUserPo sysUserPos = dao.listRoleAndPer(uname);
            return sysUserPos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Result listUserByRoleId(Integer rid,String uname,Integer pageindex) throws Exception{
        List<SysUserPo> sysUserPos = null;
        if (pageindex>0){
            PageHelper.startPage(pageindex,6);
            sysUserPos = dao.listUserByRoleId(rid,uname);
            return Result.success(new PageInfo<SysUserPo>(sysUserPos));
        }else{
            sysUserPos = dao.listUserByRoleId(rid,uname);
            return Result.success(sysUserPos);
        }

    }

    @Override
    public Result del(Integer uid) {
        int del = dao.del(uid);
        if (del>0){
            return Result.success();
        }else{
            return Result.fail("删除失败");
        }
    }

    @Override
    public SysUserPo findByUid(Integer uid) {
        SysUserPo userPo = dao.findByUid(uid);
        return userPo;
    }

    @Override
    @Transactional
    public Result modifyByUser(SysUserPo po) {
        po.setUpdateTime(new Date());
        int result = dao.modifyByUser(po);
        if (result>0){
            return Result.success();
        }else{
            return Result.fail("修改失败");
        }
    }
}
