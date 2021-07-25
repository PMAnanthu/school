package com.school.studentmanagementservice.proxy;

import com.school.studentmanagementservice.http.dto.CreateManger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-management-service")
public interface UserServiceProxy {
    @PostMapping("/mangers")
    String createUser(@RequestBody CreateManger manger);
}