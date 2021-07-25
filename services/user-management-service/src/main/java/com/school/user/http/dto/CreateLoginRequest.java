package com.school.user.http.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateLoginRequest {
    private String userName;
    private String password;
    private String email;
    private String name;
}
