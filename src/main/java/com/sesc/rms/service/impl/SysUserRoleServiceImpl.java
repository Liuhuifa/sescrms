package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysUserRolePo;
import com.sesc.rms.dao.SysUserRoleMapper;
import com.sesc.rms.service.inter.SysUserRoleService;
import javax.annotation.Resource;
import com.sesc.rms.po.SysUserRolePo;
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
	public int addAny(List<SysUserRolePo> list){
return mapper.addAny(list);
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