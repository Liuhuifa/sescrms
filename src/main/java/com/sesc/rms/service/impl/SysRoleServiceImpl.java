package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.dao.SysRoleMapper;
import com.sesc.rms.service.inter.SysRoleService;
import javax.annotation.Resource;
import com.sesc.rms.po.SysRolePo;
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
    public List<SysRolePo> listRoles(Integer uid) {
        return mapper.listRoles(uid);
    }
}