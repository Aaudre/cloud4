package com.example.photoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhotoappApplication {

	public static void main(String[] args) {
		// Define a porta do servidor como 8081
		System.setProperty("server.port", "8081");
		SpringApplication.run(PhotoappApplication.class, args);
	}

}
