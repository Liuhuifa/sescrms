package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;
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
			if (flag==-1){
				List<SysRolePo> sysRolePos = mapper.listRoles(uid, flag);
				return Result.success(sysRolePos);
			}else if (flag==1){
				List<SysRolePo> authorize = mapper.listRoles(uid, 0);//未授权角色
				List<SysRolePo> authorized = mapper.listRoles(uid, 1);//已授权角色
				Map<String,Object> maps = new HashMap<>();
				maps.put("authorize",authorize);
				maps.put("authorized",authorized);
				return Result.success(maps);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("服务器异常");
		}
		return Result.fail("服务器被舔坏了");
    }
}