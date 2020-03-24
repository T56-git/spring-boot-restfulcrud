package com.wlt.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: T56
 * @Date: 2020-03-22 23:25
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

     //配置druid监控源
    //第一步：配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParamMap = new HashMap<String,String>();
        initParamMap.put("loginUsername","admin");
        initParamMap.put("loginPassword","1234");
        initParamMap.put("allow","");//配置允许访问的ip  比如 allow=localhost,默认或者为null 则所有都能访问
//        map.put("deny","");//配置拒接访问的ip
        servletRegistrationBean.setInitParameters(initParamMap);
        return servletRegistrationBean;
    }

    //第二部：配置一个web监控filter
    @Bean
    public FilterRegistrationBean  webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParamMap = new HashMap<String,String>();
        //配置不拦截的请求 静态资源 + 监控请求相关  的不拦截
        initParamMap.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParamMap);
        //设置拦截范围
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
