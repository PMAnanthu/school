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

    private static final String SKYHAWKS_LOGIN_DETAILS = "Skyhawks Login details.";
    private static final String SKYHAWKS_UPDATE_DETAILS = "Skyhawks account details update.";
    private static final String MESSAGE = "Hi %s, \n\nThanks for register with Skyhawks.\n";

    private static final String LOGIN_MESSAGE = "\nPlease find the login details. \n\nUsername:\t%s \n\nPassword:\t%s";
    private static final String UPDATE_PASSWORD_MESSAGE = "Hi %s, \n\nThanks form Skyhawks.\nYour profile got updated" +
            " successfully.";

    private final JavaMailSender mailSender;

    public void sendCreateUserMail(MailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getTo());
        mailMessage.setFrom(ApplicationConstants.APP_EMAIL);
        mailMessage.setSubject(SKYHAWKS_LOGIN_DETAILS);
        mailMessage.setText(String.format(MESSAGE + LOGIN_MESSAGE, request.getName(), request.getUserName(), request.getPassword()));
        mailSender.send(mailMessage);
    }

    public void sendUpdateUserMail(MailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getTo());
        mailMessage.setFrom(ApplicationConstants.APP_EMAIL);
        mailMessage.setSubject(SKYHAWKS_UPDATE_DETAILS);
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            mailMessage.setText(String.format(UPDATE_PASSWORD_MESSAGE, request.getName()));
        } else {
            mailMessage.setText(String.format(UPDATE_PASSWORD_MESSAGE + LOGIN_MESSAGE, request.getName(), request.getUserName(), request.getPassword()));
        }
        mailSender.send(mailMessage);
    }
}
