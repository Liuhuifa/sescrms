package com.sesc.rms.service.inter;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysRolePerPo;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;

@Service
public interface SysRolePerService {
	Result addOne(SysRolePerPo sysRolePer);
	int addAny(List<SysRolePerPo> list);
	int modify(SysRolePerPo sysRolePer);
	Result del(int pid, int rid);
	SysRolePerPo selectOne(SysRolePerPo sysRolePer);
}