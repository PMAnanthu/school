/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.notification.services;

import com.skyhawks.notification.config.ApplicationConstants;
import com.skyhawks.notification.request.MailRequest;
import lombok.Data;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendCreateUserMail(MailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getTo());
        mailMessage.setFrom(ApplicationConstants.APP_EMAIL);
        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getBody());
        mailSender.send(mailMessage);
    }

}
