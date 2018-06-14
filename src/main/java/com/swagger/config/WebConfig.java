package com.swagger.config;

import com.swagger.handler.RequestHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author 袁伟倩
 * @Date 2018-6-710:56
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private RequestHandler requestHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(requestHandler).addPathPatterns("/**").
                excludePathPatterns("/swagger-resources/**","/swagger-ui.html","/webjars/**","/v2/api-docs");
    }
}