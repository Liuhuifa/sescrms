package com.sesc.rms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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
        return "load";
    }
//    用户添加页面
    @GetMapping("user-add")
    public String userAdd(){
        return "user-add";
    }
}
