package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysRolePerPo;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.dao.SysRoleMapper;
import com.sesc.rms.service.inter.SysRolePerService;
import com.sesc.rms.service.inter.SysRoleService;
import javax.annotation.Resource;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Resource
	private SysRoleMapper mapper;
	@Resource
	private SysRolePerService srpService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public Result addOne(SysRolePo po,Integer[]pids){
		Result result = new Result();
		mapper.addOne(po);
		Integer rid = po.getRid();
		if (rid>0){//如果角色添加成功以后再进行授权
			List<SysRolePerPo> lists = new ArrayList<>();
			for (int i=0; i<pids.length; i++){
				lists.add(new SysRolePerPo(rid,pids[i]));
			}
			if (!lists.isEmpty())//判断是否给该角色授权了
			    srpService.addAny(lists);
			result.setCode(1);
		}else{
			result.setCode(0);
			new RuntimeException();
		}
		return result;
	}

	@Override
	public int addAny(List<SysRolePo> list){
		return mapper.addAny(list);
	}

	@Override
	public int modify(SysRolePo sysRole){
		return mapper.modify(sysRole);
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	@Override
	public Result del(int id){
		Result result = new Result();
		int del = mapper.del(id);
		if (del>0){
			result.setCode(1);
		}else{
			result.setCode(0);
		}
		return result;
	}

	@Override
	public Result selectOne(SysRolePo sysRole){
        try {
            SysRolePo sysRolePo = mapper.selectOne(sysRole);
            return Result.success(sysRolePo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("服务器gg");
        }


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