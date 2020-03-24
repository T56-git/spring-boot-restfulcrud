package com.wlt.springboot.compent;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: T56
 * @Date: 2020-03-15 21:31
 */
public class MyLocale implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取转换语言连接传入的语言参数
        String l = request.getParameter("l");
        Locale locale = new Locale("zh", "CN");;
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}

