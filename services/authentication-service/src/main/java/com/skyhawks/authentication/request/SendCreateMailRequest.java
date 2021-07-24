package com.skyhawks.authentication.request;

import lombok.Data;

@Data
public class SendCreateMailRequest {
    private String to;
    private String userName;
    private String name;
    private String password;
}
