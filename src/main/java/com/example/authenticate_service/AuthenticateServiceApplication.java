package com.example.authenticate_service;

import com.example.authenticate_service.config.JettyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource(value = "classpath:application.properties")
})
public class AuthenticateServiceApplication {
    public static final String COOKIE_NAME = "token";

    public static void main(String[] args) {
        SpringApplication.run(new Class[] {
                AuthenticateServiceApplication.class,
                JettyConfig.class
        }, args);
    }

}
