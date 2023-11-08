package com.example.number;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Классдля запуска приложения в контейнере сервлетов.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Конфигурирует приложение для запуска в контейнере сервлетов.
	 * 
	 * @param application объект-построитель приложения
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NumberGeneratorApplication.class);
	}

}
