package com.sesc.rms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String id = request.getSession().getId();
        String sessionid = null;
        Cookie[] cookies = request.getCookies();
        for (int i=0; i<cookies.length; i++){
            if (cookies[i].getName().equals("JSESSIONID")){
                cookies[i].getValue();
            }
        }
        System.out.println(id.equals(sessionid)+">>>>>>>>>>>>>>>>>");
        return true;
    }
}
