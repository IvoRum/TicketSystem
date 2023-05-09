package com.diploma.ticket.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedOrigins("http://127.0.0.1:5173/")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
        /*
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5173/")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
        /*
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders("Authorization");

         */
    }
}

