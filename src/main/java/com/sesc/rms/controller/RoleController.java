package com.sesc.rms.controller;

import com.sesc.rms.service.inter.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private SysRoleService service;

    @PostMapping("listRoles")
    @ResponseBody
    public Object listRoles(@RequestParam(name = "uid",required = false,defaultValue = "-1")Integer uid){
        return service.listRoles(uid);
    }

}
