package com.sesc.rms.dao;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePerPo;
public interface SysRolePerMapper {
	int addOne(SysRolePerPo sysRolePer);
	int addAny(List<SysRolePerPo> list);
	int modify(SysRolePerPo sysRolePer);	int del(int id);
	SysRolePerPo selectOne(SysRolePerPo sysRolePer);
}