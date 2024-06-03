package com.funnygorilla.tinybankcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "contractType", description = "server side contract")
@SpringBootApplication(scanBasePackages = {"com.funnygorilla.tinybankcustomer"})
@OpenAPIDefinition(info = @Info(
		title = "Customer Management Domain Service", 
		description = "An API showcase demonstrating how to automatically generate an openapi spec and client based on this.",
		contact = @Contact(
			    name = "Platform Architecture Team",
			    url = "https://www.ing.com.au",
			    email = "platform@ing.com.au"),
		version = "0.0.1"		
	))
public class TinybankcustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinybankcustomerApplication.class, args);
	}

}
