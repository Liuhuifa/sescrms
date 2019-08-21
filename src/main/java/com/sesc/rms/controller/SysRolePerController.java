package com.sesc.rms.controller;

import com.sesc.rms.po.SysRolePerPo;
import com.sesc.rms.service.inter.SysRolePerService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("role-per")
public class SysRolePerController{
	@Resource
	private SysRolePerService service;

	@PostMapping("add")
//	@PutMapping("add")
	@ResponseBody
	public Object add(@RequestParam(name = "rid") Integer rid,
					  @RequestParam(name = "pid")Integer pid){
		return service.addOne(new SysRolePerPo(rid,pid));
	}
	@PostMapping("del")
//	@DeleteMapping("del")
	@ResponseBody
	public Object del(@RequestParam(name = "rid") Integer rid,
					  @RequestParam(name = "pid")Integer pid){
		return service.del(pid,rid);
	}
}