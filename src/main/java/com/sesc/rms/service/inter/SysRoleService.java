package com.sesc.rms.service.inter;
import java.util.*;
import java.lang.*;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.util.Result;

public interface SysRoleService {
	Result addOne(SysRolePo po,Integer[]pids);
	int addAny(List<SysRolePo> list);
	int modify(SysRolePo sysRole);

	Result del(int id);
	Result selectOne(SysRolePo sysRole);

	Result listRoles(Integer uid, Integer flag);

	PageInfo listRoles(Integer pageindex, Integer pagesize,Integer flag);
}