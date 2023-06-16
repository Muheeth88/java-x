package com.xapp.xjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class XJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(XJavaApplication.class, args);
	}
}
