package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePerPo;
import com.sesc.rms.dao.SysRolePerMapper;
import com.sesc.rms.service.inter.SysRolePerService;
import javax.annotation.Resource;
import com.sesc.rms.po.SysRolePerPo;
import org.springframework.stereotype.Service;

@Service
public class SysRolePerServiceImpl implements SysRolePerService {
	@Resource
	private SysRolePerMapper mapper;

	@Override
	public int addOne(SysRolePerPo sysRolePer){
		return mapper.addOne(sysRolePer);
	}

	@Override
	public int addAny(List<SysRolePerPo> list){
return mapper.addAny(list);
	}

	@Override
	public int modify(SysRolePerPo sysRolePer){
		return mapper.modify(sysRolePer);
	}

	@Override
	public int del(int id){
		return mapper.del(id);
	}

	@Override
	public SysRolePerPo selectOne(SysRolePerPo sysRolePer){
		return mapper.selectOne(sysRolePer);
	}
}