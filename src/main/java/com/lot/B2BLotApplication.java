package com.lot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class B2BLotApplication  extends SpringBootServletInitializer {

	
	
	public static void main(String[] args) {
		SpringApplication.run(B2BLotApplication.class, args);
	}
	
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(B2BLotApplication.class);
    }
	
}
