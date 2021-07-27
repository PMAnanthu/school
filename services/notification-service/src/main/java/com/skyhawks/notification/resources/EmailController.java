/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.skyhawks.notification.resources;

import com.skyhawks.notification.request.MailRequest;
import com.skyhawks.notification.services.EmailService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Data
@RestController
@RequestMapping("/mails")
public class EmailController {

    private final EmailService emailService;

    @GetMapping(path = "test")
    public String test(){
        return "Success";
    }

    @PostMapping(path = "/send")
    public void sendCreateUserMail(@Valid @RequestBody MailRequest request){
        emailService.sendCreateUserMail(request);
    }

}
