package com.quest.questserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class QuestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestServerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/actuator").allowedOrigins("http://localhost:3000");
				registry.addMapping("/game").allowedOrigins("http://localhost:3000");
				registry.addMapping("/players").allowedOrigins("http://localhost:3000");
				registry.addMapping("/ws").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
