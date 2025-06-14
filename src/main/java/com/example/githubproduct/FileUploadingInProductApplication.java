package com.example.githubproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main entry point for the Product Image CRUD Application.
 * This is a Spring Boot application that supports product management with image upload, 
 * retrieval, update, deletion, and download functionality using RESTful APIs.
 */
@SpringBootApplication
public class FileUploadingInProductApplication {
	 /**
     * Main method to launch the Spring Boot application.
     * 
     * @param args Command-line arguments
     */
	public static void main(String[] args) {
		SpringApplication.run(FileUploadingInProductApplication.class, args);
	}

}
