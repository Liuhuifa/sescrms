package com.sesc.rms.service.inter;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePo;
public interface SysRoleService {
	int addOne(SysRolePo sysRole);
	int addAny(List<SysRolePo> list);
	int modify(SysRolePo sysRole);	int del(int id);
	SysRolePo selectOne(SysRolePo sysRole);
}