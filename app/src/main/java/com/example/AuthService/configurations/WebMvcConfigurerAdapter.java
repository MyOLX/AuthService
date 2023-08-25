package com.example.AuthService.configurations;

import jakarta.servlet.ServletContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public interface WebMvcConfigurerAdapter extends WebMvcConfigurer {

    public void onStartup(ServletContext servletContext);

}
