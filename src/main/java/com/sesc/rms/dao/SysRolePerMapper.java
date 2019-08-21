package com.sesc.rms.dao;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePerPo;
import org.apache.ibatis.annotations.Param;

public interface SysRolePerMapper {
	int addOne(SysRolePerPo sysRolePer);
	int addAny(List<SysRolePerPo> list);
	int modify(SysRolePerPo sysRolePer);
	int del(@Param("pid") int pid, @Param("rid")int rid);
	SysRolePerPo selectOne(SysRolePerPo sysRolePer);
}