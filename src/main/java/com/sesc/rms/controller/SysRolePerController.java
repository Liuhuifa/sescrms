package com.sesc.rms.controller;

import com.sesc.rms.service.inter.SysRolePerService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class SysRolePerController{
	@Resource
	private SysRolePerService service;


}