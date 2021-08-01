package com.school.academic.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-management-service")
public interface UserServiceProxy {

    @GetMapping("/users/{user}/type/by/{type}")
    UserType checkUserType(@PathVariable String user, @PathVariable String type);
}
