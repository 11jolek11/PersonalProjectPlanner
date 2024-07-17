package com.project.planner;

import com.project.planner.configs.StorageConfig;
//import com.project.planner.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfig.class)
public class PlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner init(StorageService storageService) {
//		return (args) -> {
//			storageService.deleteAll();
//			storageService.initialize();
//		};
//	}
}
