package com.sesc.rms.service.impl;
import java.util.*;
import java.lang.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysPerPo;
import com.sesc.rms.dao.SysPerMapper;
import com.sesc.rms.service.inter.SysPerService;
import javax.annotation.Resource;
import com.sesc.rms.po.SysPerPo;
import com.sesc.rms.util.Result;
import org.springframework.stereotype.Service;

@Service
public class SysPerServiceImpl implements SysPerService {
	@Resource
	private SysPerMapper mapper;

	@Override
	public int addOne(SysPerPo sysPer){
		return mapper.addOne(sysPer);
	}

	@Override
	public int addAny(List<SysPerPo> list){
return mapper.addAny(list);
	}

	@Override
	public int modify(SysPerPo sysPer){
		return mapper.modify(sysPer);
	}

	@Override
	public int del(int id){
		return mapper.del(id);
	}

	@Override
	public SysPerPo selectOne(SysPerPo sysPer){
		return mapper.selectOne(sysPer);
	}

	@Override
	public PageInfo<SysPerPo> listPers(Integer pageindex, Integer pagesize, Integer flag) {
		if (flag==null){
			PageHelper.startPage(pageindex,pagesize);
			List<SysPerPo> sysPerPos = mapper.listPers(null, null);
			PageInfo<SysPerPo> info = new PageInfo<>(sysPerPos);
			return info;
		}
		return null;
	}
	@Override
	public Result listPers(Integer rid,Integer flag) {
		try {
			if (rid != null){
				List<SysPerPo> authorized = mapper.listPers(rid, 0);//已经授权的
				List<SysPerPo> authorize = mapper.listPers(rid, 1);//未拥有的权限
				Map<String,Object> map = new HashMap<>();
				map.put("authorized",authorized);
				map.put("authorize",authorize);
				return Result.success(map);
			}else {
				List<SysPerPo> sysPerPos = mapper.listPers(null, null);
				return Result.success(sysPerPos);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("服务器凉了");
		}
	}

	@Override
	public List<SysPerPo> listPers() {
		return mapper.listPers(null, null);
	}
}