package com.isil.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EntityScan(basePackages = { "com.isil" })
@org.springframework.context.annotation.ComponentScan(basePackages = { "com.isil" })
@org.springframework.data.jpa.repository.config.EnableJpaRepositories(basePackages = { "com.isil" })
public class BankingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingsystemApplication.class, args);
	}

}
