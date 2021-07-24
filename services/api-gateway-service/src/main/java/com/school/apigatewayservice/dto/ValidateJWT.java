package com.school.apigatewayservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateJWT {
    private String jwt;
}
