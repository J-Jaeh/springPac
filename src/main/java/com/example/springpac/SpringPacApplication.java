package com.example.springpac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPacApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPacApplication.class, args);
    }

}
