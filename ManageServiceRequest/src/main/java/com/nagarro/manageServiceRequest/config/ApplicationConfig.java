package com.nagarro.manageServiceRequest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {
	 @Bean
	   public ModelMapper modelMapper() {
		 return new ModelMapper();
	       }
	
}
