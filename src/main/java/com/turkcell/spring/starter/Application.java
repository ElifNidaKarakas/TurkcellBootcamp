package com.turkcell.spring.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
