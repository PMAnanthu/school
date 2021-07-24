package com.skyhawks.authentication.resources.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationResponse {
    private String jwt;
    private String userId;
    private String userName;
    private boolean newUser;
    private boolean active;
}