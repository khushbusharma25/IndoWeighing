package com.ind.weighing.Indo_weighing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("spring.profiles.active")
	private String profile;
	
	
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        if(profile.equalsIgnoreCase("dev")){
            registry.addMapping("/**").allowedOrigins("http://localhost:4200");
        }
    }
}
