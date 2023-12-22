package com.josh.hotelmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class})

public class HotelMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelMgmtApplication.class, args);
	}

}
