package com.renannrocha.projects.taskmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskManagerApplication {

	@Bean
	public CommandLineRunner executar(){
		return args -> {
			System.out.println("deu certo");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
