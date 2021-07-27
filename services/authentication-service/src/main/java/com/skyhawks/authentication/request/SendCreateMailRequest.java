package com.skyhawks.authentication.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SendCreateMailRequest {
    @NotNull(message = "To address should not be null")
    @NotEmpty(message = "To address should not be empty")
    @Email(message = "To address should be proper mail address")
    private String to;

    @NotNull(message = "Subject should not be null")
    @NotEmpty(message = "Subject should not be empty")
    private String subject;

    @NotNull(message = "Body should not be null")
    @NotEmpty(message = "Body should not be empty")
    private String body;
}
