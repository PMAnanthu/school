package com.school.user.resources;

import com.school.user.http.dto.UserResponse;
import com.school.user.service.UserMappingService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class UserResource {

    private final UserMappingService userMappingService;

    @GetMapping("/users/{userId}")
    public UserResponse getUser(@PathVariable String userId){
        return userMappingService.findUser(userId);
    }

    @GetMapping("/admin")
    public void createAdmin(@PathVariable String userId){
         userMappingService.addAdmin();
    }
}
