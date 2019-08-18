package com.sesc.rms.service.inter;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePerPo;
import org.springframework.stereotype.Service;

@Service
public interface SysRolePerService {
	int addOne(SysRolePerPo sysRolePer);
	int addAny(List<SysRolePerPo> list);
	int modify(SysRolePerPo sysRolePer);	int del(int id);
	SysRolePerPo selectOne(SysRolePerPo sysRolePer);
}