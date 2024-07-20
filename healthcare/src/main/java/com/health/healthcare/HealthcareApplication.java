package com.health.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity(debug = true)
public class HealthcareApplication {

    public static void main(final String[] args) {
        SpringApplication.run(HealthcareApplication.class, args);
    }

}
