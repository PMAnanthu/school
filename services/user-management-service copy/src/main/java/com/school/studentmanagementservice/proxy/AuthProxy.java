package com.school.studentmanagementservice.proxy;

import com.school.studentmanagementservice.http.dto.CreateLoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="authentication-service")
public interface AuthProxy {
    @PostMapping("/sign-up")
    public String createLogin(@RequestBody CreateLoginRequest request);
}
