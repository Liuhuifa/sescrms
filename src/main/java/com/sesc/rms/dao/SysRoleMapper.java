package com.sesc.rms.dao;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePo;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
	int addOne(SysRolePo po);
	int addAny(List<SysRolePo> list);
	int modify(SysRolePo sysRole);	int del(int id);
	SysRolePo selectOne(SysRolePo sysRole);
	List<SysRolePo> listRoles(@Param("uid")Integer uid,@Param("flag")Integer flag);
}