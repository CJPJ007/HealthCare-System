package com.health.healthcare.rest;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Slf4j
@RequestMapping("/api/health")
public class HealthResource {
    
    @Value("${health.categories}")
    private String categories;

    @GetMapping("/getCategories")
    public ResponseEntity<String> getCategories() throws InterruptedException {
        log.info("Inside getCategories");
        Thread.sleep(1000);
        return ResponseEntity.ok().body(categories);
    }
    
}
