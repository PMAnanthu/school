/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.studentmanagementservice.resources;

import com.school.studentmanagementservice.dto.School;
import com.school.studentmanagementservice.exception.UserNotFountException;
import com.school.studentmanagementservice.http.dto.CreateSchool;
import com.school.studentmanagementservice.service.SchoolService;
import lombok.Data;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
@RestController
public class SchoolResources {

    private final SchoolService schoolService;

    @GetMapping("/schools")
    public List<School> getAllSchool() {
        return schoolService.findAll();
    }

    @GetMapping("/schools/{uuid}")
    public School getSchool(@PathVariable String uuid) throws UserNotFountException {
        return schoolService.findSchool(UUID.fromString(uuid));
    }

    @PostMapping("/schools")
    public ResponseEntity<String> insertSchool(@Valid @RequestBody CreateSchool create,
                                               @RequestHeader String userId) {
        return ResponseEntity.ok(schoolService.insertSchool(create,userId));
    }

}
