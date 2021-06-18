package com.cry.flutter.admin.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cry.flutter.admin.common.MyMetaObjectHandler;
import com.cry.flutter.admin.common.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MybatisPlusConfig implements WebMvcConfigurer {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        return globalConfig;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(RequestInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public RequestInterceptor RequestInterceptor() {
        return new RequestInterceptor();
    }

}
