package com.wlt.springboot.config;

import com.wlt.springboot.compent.LoginHandlerInterceptor;
import com.wlt.springboot.compent.MyLocale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: T56
 * @Date: 2020-03-15 19:16
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 覆写此方法来注册 请求映射到视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main").setViewName("dashboard");
    }

    /**
     * 覆写此方法来注册自己的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自己的拦截器，拦截所有请求，排除登陆相关的请求 static下的静态资源springboot已经做了处理，不会被拦截
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/index.html","/user/login");
    }

    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocale();
    }

}


