package com.example.myhead.second.common.config.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通过这个配置类，可以将 LoginInterceptor 配置器添加进来
 * 之前我们在拦截器 LoginInterceptor 中配置的路径，即 index，触发的时机是在拦截器生效之后。
 * 也就是说，我们访问一个 URL，会首先通过 Configurer 判断是否需要拦截，
 * 如果需要，才会触发拦截器 LoginInterceptor，根据我们自定义的规则进行再次判断。
 *
 *
 * 而后端登录拦截，适用于 前后端项目整合在一起时
 * 前后端分离的项目是不推荐使用后端登录拦截的，而一般使用前端登录拦截 Vuex
 */
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //对所有路径都使用拦截器，除了 index.html 这个路径
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }
}
