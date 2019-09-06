package com.sesc.rms.controller;

import com.sesc.rms.service.inter.SysRoleService;
import com.sesc.rms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @Autowired
    private SysRoleService rservice;
//    跳转首页
    @GetMapping("/index")
    public String index(){
        return "index";
    }
//    跳转登录页面
    @GetMapping("login")
    public String login(){
        return "login";
    }
//    跳转用户列表
    @GetMapping("user_list")
    public String userList(){
        return "user_list";
    }
//    测试
    @GetMapping("load")
    public String load(){
        return "test";
    }
    //    用户添加页面
    @GetMapping("user-add")
    public ModelAndView userAdd(){
        Result result = rservice.listRoles(null, null);
        System.out.println(result.getData());
        ModelAndView mv = new ModelAndView("user-add");
        mv.addObject("datas",result);
        return mv;
    }
    @GetMapping("customer/add")
    public String cusAdd(){
        return "client/client-add";
    }
//    修改密码
    @GetMapping("modifyp")
    public String modify(){
        return "user/modify";
    }
    @GetMapping("error/500")
    public String error(){
        return "error/500";
    }
    @GetMapping("error/404")
    public String error1(){
        return "error/404";
    }
}
