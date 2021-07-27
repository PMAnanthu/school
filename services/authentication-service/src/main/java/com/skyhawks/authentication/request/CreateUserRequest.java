package com.skyhawks.authentication.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {
    @NotNull
    @NotEmpty
    private String userName;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String name;

    private String schoolName;
}
