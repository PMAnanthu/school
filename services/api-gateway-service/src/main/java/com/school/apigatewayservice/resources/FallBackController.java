/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 28/06/21
*/
package com.school.apigatewayservice.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @RequestMapping("notificationServiceFallBack")
    public String notificationServiceFallBackMethod(){
        return "Notification Service is taking longer than expected.";
    }

    @RequestMapping("schoolServiceFallBack")
    public String schoolServiceFallBackMethod(){
        return "School Service is taking longer than expected.";
    }

    @RequestMapping("authenticationServiceFallBack")
    public String authenticationFallBackMethod(){
        return "Authentication Service is taking longer than expected.";
    }

    @RequestMapping("userServiceFallBack")
    public String userServiceFallBackMethod(){
        return "User Service is taking longer than expected.";
    }
}
