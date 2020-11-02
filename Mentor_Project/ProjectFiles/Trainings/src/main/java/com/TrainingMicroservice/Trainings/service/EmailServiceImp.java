package com.TrainingMicroservice.Trainings.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailServiceImp {
    @Autowired
    JavaMailSender emailsender;

    public void sendproposalmessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("risab.278@gmail.com");
        message.setTo("risab.278@gmail.com");
        message.setSubject("New training proposed");
        message.setText("Your training has been proposed,Wait for the approval.ThankYou!");
        emailsender.send(message);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("risab.278@gmail.com");
        mailSender.setPassword("ktcmgnvgknbdzfmx");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
}
