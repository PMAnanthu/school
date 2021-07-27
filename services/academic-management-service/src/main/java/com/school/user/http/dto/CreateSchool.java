package com.school.user.http.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateSchool {
    @NotEmpty(message = "User name should not be empty")
    @NotNull(message = "User name should not be null")
    private String userName;
    @NotEmpty(message = "Name should not be empty")
    @NotNull(message = "Name should not be null")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @NotNull(message = "Email should not be null")
    private String email;
    private String address;
    private String mobile;
    private String webSite;
}
