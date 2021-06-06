package com.accenture.academico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EntityScan(basePackages = {"com.accenture.academico.model"})
@ComponentScan(basePackages = {"com.accenture.academico.*"})
@EnableJpaRepositories(basePackages = {"com.accenture.academico.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class AccentureacademicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccentureacademicoApplication.class, args);
	}

}
