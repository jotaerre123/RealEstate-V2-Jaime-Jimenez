package com.salesianostriana.dam.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class RealestateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);
	}

}
