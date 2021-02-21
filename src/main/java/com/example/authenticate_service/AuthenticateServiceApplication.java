package com.example.authenticate_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = "classpath:application.properties")
})
public class AuthenticateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticateServiceApplication.class, args);
    }

}
