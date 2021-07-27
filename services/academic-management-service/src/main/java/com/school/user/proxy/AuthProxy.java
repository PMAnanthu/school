package com.school.user.proxy;

import com.school.user.http.dto.CreateLoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service")
public interface AuthProxy {

    @PostMapping("/sign-up")
    String createLogin(@RequestBody CreateLoginRequest request);

    @DeleteMapping("/sign-out/{id}")
    void deleteLogin(@PathVariable String id);
}
