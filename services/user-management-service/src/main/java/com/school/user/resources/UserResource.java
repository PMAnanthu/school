package com.school.user.resources;

import com.school.user.http.dto.AdminResponse;
import com.school.user.http.dto.UserResponse;
import com.school.user.service.UserMappingService;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/admin/create")
    public AdminResponse createAdmin(){
         return userMappingService.addAdmin();
    }

    @DeleteMapping("/admin/{token}")
    public void DeleteAdmin(@PathVariable String token){
         userMappingService.deleteAdmin(token);
    }
}
