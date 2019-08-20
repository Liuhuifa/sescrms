package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysUserRolePo;
import com.sesc.rms.dao.SysUserRoleMapper;
import com.sesc.rms.service.inter.SysUserRoleService;
import javax.annotation.Resource;

import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	@Resource
	private SysUserRoleMapper mapper;

	@Override
	public int addOne(SysUserRolePo sysUserRole){
		return mapper.addOne(sysUserRole);
	}

	@Override
	public Result addAny(List<SysUserRolePo> list){

		try {
			int result = mapper.addAny(list);
			if (result>0)
				return Result.success();
			else
				return Result.fail("添加失败");
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("服务器异常");
		}
	}

	@Override
	public int modify(SysUserRolePo sysUserRole){
		return mapper.modify(sysUserRole);
	}

	@Override
	public int del(int id){
		return mapper.del(id);
	}

	@Override
	public SysUserRolePo selectOne(SysUserRolePo sysUserRole){
		return mapper.selectOne(sysUserRole);
	}
}