package com.school.user.http.dto;

import lombok.Data;

@Data
public class CreateLoginRequest {
    private String userName;
    private String password;
    private String email;
    private String name;
    private String schoolName;
}
