package com.school.studentmanagementservice.http.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateParent {
    @NotEmpty(message = "User name should not be empty")
    @NotNull(message = "User name should not be null")
    private String userName;
    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "First name should not be null")
    private String firstName;
    private String middleName;
    private String lastName;
    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "First name should not be null")
    private String email;
    private String address;
    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "First name should not be null")
    private String mobile;
}
