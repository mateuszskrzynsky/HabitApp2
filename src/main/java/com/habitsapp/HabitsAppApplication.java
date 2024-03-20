package com.habitsapp;

import com.habitsapp.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HabitsAppApplication {

//    private EmailService emailService;
//
//    public HabitsAppApplication(EmailService emailService) {
//        this.emailService = emailService;
//    }

    public static void main(String[] args) {

        SpringApplication.run(HabitsAppApplication.class, args);

    }

//    private void email(){
//        emailService.sendMessage("mateusz.skrzynsky@gmail.com", "Welcome in HabitApp","Hi thanks for use my app!");
//    }

}
