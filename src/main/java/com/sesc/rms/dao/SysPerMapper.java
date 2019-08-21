package com.sesc.rms.dao;
import java.util.*;
import java.lang.*;
import com.sesc.rms.po.SysPerPo;
public interface SysPerMapper {
	int addOne(SysPerPo sysPer);
	int addAny(List<SysPerPo> list);
	int modify(SysPerPo sysPer);	int del(int id);
	SysPerPo selectOne(SysPerPo sysPer);
	List<SysPerPo> listPers(Integer rid,Integer flag);
}