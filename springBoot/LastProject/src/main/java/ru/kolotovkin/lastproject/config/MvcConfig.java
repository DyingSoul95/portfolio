package ru.kolotovkin.lastproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/messages").setViewName("messages");
        registry.addViewController("/").setViewName("messages");
        registry.addViewController("/login").setViewName("login");
    }
}
