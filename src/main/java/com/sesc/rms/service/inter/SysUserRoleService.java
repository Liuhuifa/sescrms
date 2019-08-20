package com.sesc.rms.service.inter;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysUserRolePo;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;

@Service
public interface SysUserRoleService {
	int addOne(SysUserRolePo sysUserRole);
	Result addAny(List<SysUserRolePo> list);
	int modify(SysUserRolePo sysUserRole);	int del(int id);
	SysUserRolePo selectOne(SysUserRolePo sysUserRole);
}