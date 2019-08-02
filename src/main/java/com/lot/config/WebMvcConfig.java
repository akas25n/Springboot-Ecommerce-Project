package com.lot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMBeanExport
public class WebMvcConfig implements WebMvcConfigurer{

	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	 
	 @Bean
	 public MBeanExporter exporter()
	 {
	     final MBeanExporter exporter = new MBeanExporter();
	     exporter.setAutodetect(true);
	     exporter.setExcludedBeans("dataSource");
	     return exporter;
	 }
}
