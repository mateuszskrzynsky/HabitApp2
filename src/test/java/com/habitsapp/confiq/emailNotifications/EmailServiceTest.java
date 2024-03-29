package com.habitsapp.confiq.emailNotifications;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class EmailServiceTest {

        @Mock
        private JavaMailSender javaMailSender;

        @InjectMocks
        private EmailService emailService;

        @Test
        void sendMessage_ShouldSendEmail() {
            // Given
            String to = "test@test.com";
            String subject = "Test Subject";
            String text = "Test message";

            // When
            emailService.sendMessage(to, subject, text);

            // Then
            verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
        }
}