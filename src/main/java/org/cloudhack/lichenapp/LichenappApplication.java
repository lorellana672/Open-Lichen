package org.cloudhack.lichenapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LichenappApplication {
	public static void main(String[] args) {
		SpringApplication.run(LichenappApplication.class, args);
	}
}
