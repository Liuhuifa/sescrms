package com.sesc.rms.controller;

import com.sesc.rms.service.inter.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request,String username,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username.trim(),password.trim());
        try {
            subject.login(token);
            request.getSession().setAttribute("user",username);
            return "200";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return "-1";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return "0";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "1";
        } catch (Exception e){
            e.printStackTrace();
            return "500";
        }
    }
}
