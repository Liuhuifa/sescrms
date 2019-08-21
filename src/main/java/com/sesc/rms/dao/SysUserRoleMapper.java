package com.sesc.rms.dao;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysUserRolePo;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
	int addOne(SysUserRolePo sysUserRole);
	int addAny(List<SysUserRolePo> list);
	int modify(SysUserRolePo sysUserRole);	int del(int id);
	SysUserRolePo selectOne(SysUserRolePo sysUserRole);
	int del(@Param("uid")Integer uid,@Param("rid")Integer rid);
}