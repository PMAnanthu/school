package com.school.user.http.dto;

import lombok.Data;

@Data
public class AdminResponse {
    private String token;
    private String userName;
    private String password;
}
