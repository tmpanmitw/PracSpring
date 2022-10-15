package com.sparta.pracspringcrud01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // timestamped 적용
@SpringBootApplication
public class PracSpringCrud01Application {

    public static void main(String[] args) {
        SpringApplication.run(PracSpringCrud01Application.class, args);
    }
}
