package com.nicode.gestionenfermeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GestionEnfermeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEnfermeriaApplication.class, args);
	}

}
