package ru.geekbrains.spring.first.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class SpringAppApplication {
	// Домашнее задание:
	// 1. Реализуйте полноценный REST контроллер для сущности Item с возможностью выполнения
	// CRUD операций
	// 2. Добавьте запрет на возможность указания нулевой или отрицательной цены Item'а при
	// создании нового Item'а, или модификации существующего

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}
}
