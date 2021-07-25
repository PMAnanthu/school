/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.studentmanagementservice.resources;

import com.school.studentmanagementservice.dto.Parent;
import com.school.studentmanagementservice.dto.Staff;
import com.school.studentmanagementservice.exception.UserNotFountException;
import com.school.studentmanagementservice.http.dto.CreateParent;
import com.school.studentmanagementservice.http.dto.CreateStaff;
import com.school.studentmanagementservice.service.ParentService;
import com.school.studentmanagementservice.service.TeachersService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
@RestController
public class ParentResources {

    private final ParentService parentService;

    @GetMapping("/parents")
    public List<Parent> getAllParents() {
        return parentService.findAll();
    }



    @GetMapping("/parents/{uuid}")
    public Parent getParents(@PathVariable String uuid) throws UserNotFountException {
        return parentService.findParent(UUID.fromString(uuid));
    }

    @PostMapping("/parents")
    public ResponseEntity<String> insertStaff(@Valid @RequestBody CreateParent createParent) {
        return ResponseEntity.ok(parentService.insertStaff( createParent));
    }

}