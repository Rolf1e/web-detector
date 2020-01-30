package com.rolfie.webdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebDetector {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebDetector.class);
        springApplication.run(args);
    }
}
