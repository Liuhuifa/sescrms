package com.sesc.rms.controller;

import com.sesc.rms.service.inter.SysPerService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("per")
public class PerController {
    @Autowired
    private SysPerService service;

//    跳转权限列表页面
    @GetMapping("listPers")
    public ModelAndView listPers(@RequestParam(name = "pageindex",required = false,defaultValue = "1") Integer pageindex,
                                 @RequestParam(name = "pagesize",required = false,defaultValue = "1") Integer pagesize,
                                 @RequestParam(name = "flag",required = false,defaultValue = "null")Integer flag){
        Result result = service.listPers(pageindex, pagesize,flag);
        Map<String,Object> maps = new HashMap<>();
        maps.put("code",result.getCode());
        maps.put("status",result.getData());
        return new ModelAndView("/per/per_list");
    }
//    角色查询拥有的权限
    @GetMapping("listPers1")
    @ResponseBody
    public Object listPers(@RequestParam(name = "rid",required = false,defaultValue = "null") Integer rid,
                           @RequestParam(name = "flag",required = false,defaultValue = "-1") Integer flag){
       return service.listPers(rid, flag);
    }
}
