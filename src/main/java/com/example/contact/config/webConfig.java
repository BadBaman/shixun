package com.example.contact.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class webConfig extends WebMvcConfigurerAdapter {
    @Override


    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");


    }
}