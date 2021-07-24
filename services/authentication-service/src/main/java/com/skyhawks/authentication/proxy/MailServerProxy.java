package com.skyhawks.authentication.proxy;

import com.skyhawks.authentication.request.SendCreateMailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="notification-service")
public interface MailServerProxy {
    @PostMapping("/mail/send/create-user")
    void sendMail(@RequestBody SendCreateMailRequest mailRequest);
}
