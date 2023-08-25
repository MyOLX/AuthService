package com.example.AuthService.configurations;

import com.example.AuthService.interceptors.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class AppConfig implements WebMvcConfigurerAdapter {

    @Override
    public void onStartup(ServletContext servletContext) {
        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CorsFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor());
    }
}
