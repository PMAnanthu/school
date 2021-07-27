package com.school.user.resources;

import com.school.user.exception.AdminCreateException;
import com.school.user.exception.AdminDeleteException;
import com.school.user.http.dto.AdminResponse;
import com.school.user.http.dto.UserResponse;
import com.school.user.service.UserMappingService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
public class UserResource {

    private final UserMappingService userMappingService;

    @GetMapping("/users/my-self")
    public UserResponse getUser(@RequestHeader String userId){
        return userMappingService.findUser(userId);
    }

    @GetMapping("/admin/create")
    public AdminResponse createAdmin() throws AdminCreateException {
         return userMappingService.addAdmin();
    }

    @DeleteMapping("/admin/{token}")
    public void DeleteAdmin(@PathVariable String token) throws AdminDeleteException {
         userMappingService.deleteAdmin(token);
    }
}
