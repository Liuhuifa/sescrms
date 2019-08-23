package com.sesc.rms.controller;

import com.github.pagehelper.PageInfo;
import com.sesc.rms.po.SysPerPo;
import com.sesc.rms.service.inter.SysPerService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("per")
public class PerController {
    @Autowired
    private SysPerService service;

//    跳转权限列表页面
    @GetMapping("listPers")
    public ModelAndView listPers(@RequestParam(name = "pageindex",required = false,defaultValue = "1") Integer pageindex,
                                 @RequestParam(name = "pagesize",required = false,defaultValue = "15") Integer pagesize,
                                 @RequestParam(name = "flag",required = false)Integer flag){
        PageInfo<SysPerPo> sysPerPos = service.listPers(pageindex, pagesize, flag);
        Map<String,Object> maps = new HashMap<>();
        maps.put("data",sysPerPos);
        maps.put("status",1);
        return new ModelAndView("/per/per_list",maps);
    }
//    角色查询拥有的权限
    @GetMapping("listPers1")
    @ResponseBody
    public Object listPers(@RequestParam(name = "rid",required = false) Integer rid,
                           @RequestParam(name = "flag",required = false) Integer flag){
        System.out.println(rid+","+flag);
        System.out.println("执行权限查询+>>>>>>>>>>>>>>");
       return service.listPers(rid, flag);
    }
//    所有权限查询
    @GetMapping("listPers2")
    public ModelAndView listPers(){
        List<SysPerPo> sysPerPos = service.listPers();
        sysPerPos.stream().forEach(item->{
            System.out.println(item.getPname());
        });
        Map<String,Object> map = new HashMap<>();
        map.put("data",sysPerPos);
        return new ModelAndView("role/role-add",map);
    }
}
