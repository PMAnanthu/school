package com.skyhawks.authentication.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class JWTTokenRequest {
    @NotNull
    @NotEmpty
    private String jwt;
}
