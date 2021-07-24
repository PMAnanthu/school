package com.skyhawks.authentication.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthenticationRequest {
    @NotBlank(message = "username should not be null")
    private String userName;
    @NotNull
    private String password;
}