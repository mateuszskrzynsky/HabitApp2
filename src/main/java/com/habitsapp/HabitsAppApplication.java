package com.habitsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HabitsAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(HabitsAppApplication.class, args);

    }


}
