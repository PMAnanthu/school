package com.school.studentmanagementservice.resources;

import com.school.studentmanagementservice.http.dto.UserResponse;
import com.school.studentmanagementservice.service.UserMappingService;
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
}
