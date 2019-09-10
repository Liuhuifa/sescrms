package com.sesc.rms.config;

import com.sesc.rms.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMVCConfig extends WebMvcConfigurationSupport {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/index","/index");
//        registry.
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }
//    @Bean
//    @ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
//    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter(){
//        return new OrderedHiddenHttpMethodFilter();
//    }

//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(new LoginInterceptor());
//    }
}
