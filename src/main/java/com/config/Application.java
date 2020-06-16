package com.config;

import javax.persistence.Entity;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@SpringBootApplication
@ComponentScan(basePackages = {"com"})
@EnableJpaRepositories(basePackages = {"com.repository"})
@EntityScan(basePackages = {"com.model"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
	
}
