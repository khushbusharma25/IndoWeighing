package com.ind.weighing.Indo_weighing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ind.weighing.Indo_weighing.config.ApplicationProperties;


@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@EnableScheduling
//@ComponentScan(value = "com.ind.weighing_Indo_weighing")
@EnableConfigurationProperties(ApplicationProperties.class)
public class IndoWeighingApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(IndoWeighingApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IndoWeighingApplication.class);
    }
}
