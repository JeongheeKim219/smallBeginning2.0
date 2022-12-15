package com.project.smallbeginjava11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SmallBeginJava11Application {


    public static void main(String[] args) {
        SpringApplication.run(SmallBeginJava11Application.class, args);
    }

}
