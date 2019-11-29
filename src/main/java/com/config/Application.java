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
@EntityScan(basePackages = {"com.entity"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	
	private static final String ROOT_PASSWORD = "CAMPANHA1234";
    private static final String ROOT_USER = "campanha1234";
    private static final String MY_VIP_LIST_DATABASE = "campanha1234";
    private static final String JDBC_MYSQL_LOCALHOST_3306 = "jdbc:mysql://db4free.net/";
    private static final String COM_MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(    COM_MYSQL_JDBC_DRIVER);
        dataSource.setUrl(                JDBC_MYSQL_LOCALHOST_3306    +    MY_VIP_LIST_DATABASE);
        dataSource.setUsername(            ROOT_USER);
        dataSource.setPassword(            ROOT_PASSWORD);

        return dataSource;
    }
	
}
