package com.sesc.rms.service.inter;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysPerPo;
import org.springframework.stereotype.Service;

@Service
public interface SysPerService {
	int addOne(SysPerPo sysPer);
	int addAny(List<SysPerPo> list);
	int modify(SysPerPo sysPer);	int del(int id);
	SysPerPo selectOne(SysPerPo sysPer);
}