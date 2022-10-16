package com.sparta.pracspringcrud01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // timestamped 적용, 없으면 time관련 값에 null값 뜸
@SpringBootApplication
public class PracSpringCrud01Application {

    public static void main(String[] args) {

        SpringApplication.run(PracSpringCrud01Application.class, args);
    }
}
