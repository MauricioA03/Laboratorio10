package com.example.laboratorio10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Laboratorio10Application {

    public static void main(String[] args) {
        SpringApplication.run(Laboratorio10Application.class, args);
    }

}
