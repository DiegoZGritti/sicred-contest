package com.sicredi_contest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.api.gateway.enitities")
@ComponentScan(basePackages = {"com.api.controllers", "com.api.usecases", "com.api.gateway", "com.api.repositories"})
@EnableJpaRepositories(basePackages = "com.api.repositories")
@SpringBootApplication
public class SicrediContestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SicrediContestApplication.class, args);
	}

}
