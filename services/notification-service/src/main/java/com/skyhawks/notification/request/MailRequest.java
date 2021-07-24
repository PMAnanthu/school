package com.skyhawks.notification.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MailRequest {
    @NotNull(message = "To address should not be null")
    @NotEmpty(message = "To address should not be empty")
    @Email(message = "To address should be proper mail address")
    private String to;

    private String name;

    @NotNull(message = "Username should not be null")
    @NotEmpty(message = "Username should not be empty")
    private String userName;

    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    private String password;
}
