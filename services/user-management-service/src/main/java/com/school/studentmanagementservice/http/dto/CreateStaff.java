package com.school.studentmanagementservice.http.dto;/*
Copyright @ 2021 - covist
Project : skyhawks-core
Written: ananthupm
Date : 02/07/21
*/

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateStaff {
    @NotEmpty(message = "User name should not be empty")
    @NotNull(message = "User name should not be null")
    private String userName;
    @NotEmpty(message = "School should not be empty")
    @NotNull(message = "School should not be null")
    private String school;
    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "First name should not be null")
    private String firstName;
    private String lastName;
    private String middleName;
    @NotEmpty(message = "Email should not be empty")
    @NotNull(message = "Email should not be null")
    private String email;
    @NotNull(message = "Mobile should not be null")
    private String mobileNumber;
    private String address;
}
