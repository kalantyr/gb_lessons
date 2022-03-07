package ru.geekbrains.spring.first.app;

import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
public class SpringAppApplication {
	EntityManagerFactory factory;

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Bean
	public EntityManagerFactory getFactory(){
		if (factory == null)
			factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.buildSessionFactory();
		return factory;
	}
}
