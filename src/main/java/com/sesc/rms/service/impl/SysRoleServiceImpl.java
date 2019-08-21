package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.dao.SysRoleMapper;
import com.sesc.rms.service.inter.SysRoleService;
import javax.annotation.Resource;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Resource
	private SysRoleMapper mapper;

	@Override
	public int addOne(SysRolePo sysRole){
		return mapper.addOne(sysRole);
	}

	@Override
	public int addAny(List<SysRolePo> list){
return mapper.addAny(list);
	}

	@Override
	public int modify(SysRolePo sysRole){
		return mapper.modify(sysRole);
	}

	@Override
	public int del(int id){
		return mapper.del(id);
	}

	@Override
	public SysRolePo selectOne(SysRolePo sysRole){
		return mapper.selectOne(sysRole);
	}

    @Override
    public Result listRoles(Integer uid, Integer flag) {
		try {
			if (flag!=null && flag==-1){
//				查询用户所拥有的角色
				List<SysRolePo> sysRolePos = mapper.listRoles(uid, flag);
				return Result.success(sysRolePos);
			}else if (flag!=null && flag==1){
//				根据用户拥有角色和未拥有的角色来查询
				List<SysRolePo> authorize = mapper.listRoles(uid, 0);//未授权角色
				List<SysRolePo> authorized = mapper.listRoles(uid, 1);//已授权角色
				Map<String,Object> maps = new HashMap<>();
				maps.put("authorize",authorize);
				maps.put("authorized",authorized);
				return Result.success(maps);
			}else if (flag==null){
//				查询所有角色
				List<SysRolePo> sysRolePos = mapper.listRoles(null, null);
				return Result.success(sysRolePos);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("服务器异常");
		}
		return Result.fail("服务器异常");
    }

	@Override
	public PageInfo<SysRolePo> listRoles(Integer pageindex, Integer pagesize, Integer flag) {
		try {
			PageHelper.startPage(pageindex,pagesize);
			List<SysRolePo> sysRolePos = mapper.listRoles(null, null);
			return new PageInfo(sysRolePos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}