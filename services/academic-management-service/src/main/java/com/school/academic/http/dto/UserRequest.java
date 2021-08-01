package com.school.academic.http.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequest {
    private UUID division;
    private UUID obj;
}
