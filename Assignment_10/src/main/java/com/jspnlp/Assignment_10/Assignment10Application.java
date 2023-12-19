package com.jspnlp.Assignment_10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class})

public class Assignment10Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment10Application.class, args);
	}

}
