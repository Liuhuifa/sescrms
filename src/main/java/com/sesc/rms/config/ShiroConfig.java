package com.sesc.rms.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sesc.rms.config.shiro.SescRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig{


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(99);
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        return hashedCredentialsMatcher;
    }

    @Bean("sescRealm")
    public SescRealm sescRealm(){
        SescRealm realm = new SescRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        realm.setCacheManager(ehCacheManager());
        return realm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(sescRealm());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
//        bean.setUnauthorizedUrl("/500");

//        添加过滤器
//        Map<String, Filter> map = new HashMap<>();
//        map.put("bauthc",new FormFliter());
//        bean.setFilters(map);

//        添加过滤器链
        Map<String,String> filters = new HashMap<>();
        filters.put("/index","authc");
        filters.put("/index.html","authc");
        filters.put("/user/info","authc");
        filters.put("/user_list","authc");
        filters.put("/user_list.html","authc");
        filters.put("/user-add","authc");
        filters.put("/user-add.html","authc");
        filters.put("/role/**","authc");
        filters.put("/per/**","authc");
        filters.put("/customer/**","authc");
        filters.put("/user/login","anon");
        filters.put("/user/login.html","anon");
        filters.put("/static/**","anon");
        bean.setFilterChainDefinitionMap(filters);

        return bean;

    }
//    shiro标签与thymeleaf模板结合使用
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return manager;
    }
    /**
     * aop代理
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor processor = new LifecycleBeanPostProcessor();
        return processor;
    }
}
