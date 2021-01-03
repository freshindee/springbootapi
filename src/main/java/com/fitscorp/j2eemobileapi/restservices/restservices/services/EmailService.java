package com.fitscorp.j2eemobileapi.restservices.restservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fitscorp.j2eemobileapi.restservices.restservices.services.EmailService;

@Service
public class EmailService {
    private static final String NOREPLY_ADDRESS = "noreply@blasanka95.com";

    @Autowired
    private JavaMailSender sender;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            sender.send(message);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}