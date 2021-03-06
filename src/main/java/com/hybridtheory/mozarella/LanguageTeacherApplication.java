package com.hybridtheory.mozarella;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.hybridtheory.mozarella.configuration.general","com.hybridtheory.mozarella.configuration.authentication","com.hybridtheory.mozarella.configuration.authorization"})
public class LanguageTeacherApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageTeacherApplication.class);

	public static void main(String[] args) {
		
		LOGGER.info("Starting LanguageTeacherApplication1");
		ApplicationContext context = SpringApplication.run(LanguageTeacherApplication.class, args);
		
	} 

}
