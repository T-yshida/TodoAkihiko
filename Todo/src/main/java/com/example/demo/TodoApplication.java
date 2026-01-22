package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@EnableScheduling
	@SpringBootApplication
	public class DemoApplication {
		public static void main(String[] args) {
			SpringApplication.run(DemoApplication.class, args);
		}
	}
}
