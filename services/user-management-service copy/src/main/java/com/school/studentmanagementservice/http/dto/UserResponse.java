package com.school.studentmanagementservice.http.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private String userType;
    private String school;
}
