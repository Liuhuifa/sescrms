package com.sesc.rms.config;

import com.sesc.rms.config.shiro.SescRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig{


    @Bean("sescRealm")
    public SescRealm sescRealm(){
        SescRealm realm = new SescRealm();
        return realm;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(sescRealm());
        return securityManager;
    }
}
