package com.single.common.config;

import com.single.interceptors.MiniInterceptors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:/usr/local/tomcat8/uploads");
    }

    @Bean
    public MiniInterceptors miniInterceptor() {
        return new MiniInterceptors();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        registry.addInterceptor(miniInterceptor()).addPathPatterns("/");
        WebMvcConfigurer.super.addInterceptors(registry);
    }



}
