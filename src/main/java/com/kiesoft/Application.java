package com.kiesoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:config/*.xml")
@SpringBootApplication
public class Application {
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
    
}
