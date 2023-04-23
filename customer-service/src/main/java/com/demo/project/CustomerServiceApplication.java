package com.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.reactive.ReactiveManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}
