package com.sesc.rms.controller;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysRolePo;
import com.sesc.rms.service.inter.SysRoleService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private SysRoleService service;

    @PostMapping("listRoles")
    @ResponseBody
    public Object listRoles(@RequestParam(name = "uid",required = false,defaultValue = "-1")Integer uid,
                            @RequestParam(name = "flag",required = false,defaultValue = "-1")Integer flag){
        return service.listRoles(uid,flag);
    }

    /**
     * 跳转角色列表
     * @return
     */
    @GetMapping("listRoles1")
    public ModelAndView listRoles1(@RequestParam(name = "pageindex",required = false,defaultValue = "1")Integer pageindex,
                                  @RequestParam(name = "pagesize",required = false,defaultValue = "1")Integer pagesize){
        System.out.println(pageindex+",,,,,");
        PageInfo<SysRolePo> info = service.listRoles(pageindex, pagesize, null);
        List<SysRolePo> list = info.getList();
        Map<String,Object> map = new HashMap<>();
        map.put("datas",list);
        map.put("info",info);
        ModelAndView mv = new ModelAndView("/role/role_list",map);
        return mv;
    }

}
