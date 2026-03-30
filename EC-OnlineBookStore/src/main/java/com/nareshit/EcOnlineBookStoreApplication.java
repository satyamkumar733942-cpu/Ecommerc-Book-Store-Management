package com.nareshit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
@OpenAPIDefinition(
        info = @Info(
        title = "Ecommerc Book Store Management",
        version = "1.0",
        description = "Welcome to the Online Book Store",
        contact = @Contact(name ="Satyam Kumar",email = "satyamkumar733942@gmail.com")))

@SpringBootApplication
@EnableCaching
public class EcOnlineBookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcOnlineBookStoreApplication.class, args);
	}

}
