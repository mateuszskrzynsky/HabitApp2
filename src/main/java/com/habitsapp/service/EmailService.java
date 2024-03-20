package com.habitsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private JavaMailSender javaMailSender;

    @Async
    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mateusz.skrzynsky@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Bean
    CommandLineRunner sendWelcomeEmail(EmailService emailService) {
        return args -> {
            emailService.sendMessage("mateusz.skrzynsky@gmail.com", "Welcome in HabitApp", "Hi, thanks for using my app!");
        };
    }


}
