package com.example.rainbowend.Config;

import com.example.rainbowend.Interceptor.IndexInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Rainbow
 * 注册拦截器
 * @DATE:2023/8/8 0008
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private IndexInterceptor indexInterceptor;

    /**
     * token校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求
//        registry.addInterceptor(indexInterceptor);
        //拦截指定请求
        registry.addInterceptor(indexInterceptor).addPathPatterns("/index/**");
        //拦截指定请求，排除指定请求
//        registry.addInterceptor(indexInterceptor)
//                .addPathPatterns("index/**")     //指定拦截的请求路径
//                .excludePathPatterns("user/**");  //不拦截的请求路径


    }
}
