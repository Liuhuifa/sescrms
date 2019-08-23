package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePerPo;
import com.sesc.rms.dao.SysRolePerMapper;
import com.sesc.rms.service.inter.SysRolePerService;
import javax.annotation.Resource;
import com.sesc.rms.po.SysRolePerPo;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;

@Service
public class SysRolePerServiceImpl implements SysRolePerService {
	@Resource
	private SysRolePerMapper mapper;

	@Override
	public Result addOne(SysRolePerPo sysRolePer){
		try {
			return Result.success(mapper.addOne(sysRolePer));
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("服务器gg");
		}
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
	public Result del(int pid, int rid){
		try {
			return Result.success(mapper.del(pid,rid));
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("服务器gg");
		}
	}

	@Override
	public SysRolePerPo selectOne(SysRolePerPo sysRolePer){
		return mapper.selectOne(sysRolePer);
	}
}