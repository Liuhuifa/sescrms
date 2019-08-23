package com.sesc.rms.service.inter;
import java.util.*;
import java.lang.*;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysPerPo;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;

@Service
public interface SysPerService {
	int addOne(SysPerPo sysPer);
	int addAny(List<SysPerPo> list);
	int modify(SysPerPo sysPer);	int del(int id);
	SysPerPo selectOne(SysPerPo sysPer);
	Result listPers(Integer rid,Integer flag);
	List<SysPerPo> listPers();
	PageInfo<SysPerPo> listPers(Integer pageindex, Integer pagesize, Integer flag);
}