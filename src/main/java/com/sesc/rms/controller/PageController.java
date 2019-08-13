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
}
