/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.user.resources;

import com.school.user.dto.School;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateSchool;
import com.school.user.service.SchoolService;
import lombok.Data;
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
    public ResponseEntity<String> insertSchool(@Valid @RequestBody CreateSchool create) {
        return ResponseEntity.ok(schoolService.insertSchool(create));
    }

}
