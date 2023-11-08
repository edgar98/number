package com.example.number;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения.
 */
@SpringBootApplication
public class NumberGeneratorApplication {

	/**
	 * Основной метод запуска приложения.
	 * 
	 * @param args аргументы
	 */
	public static void main(String[] args) {
		SpringApplication.run(NumberGeneratorApplication.class, args);
	}

}
