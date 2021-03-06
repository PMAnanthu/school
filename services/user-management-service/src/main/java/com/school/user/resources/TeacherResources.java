/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.user.resources;

import com.school.user.dto.Staff;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateStaff;
import com.school.user.service.TeachersService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
@RestController
public class TeacherResources {

    private final TeachersService teachersService;

    @GetMapping("/teachers")
    public List<Staff> getAllStudent() {
        return teachersService.findAll();
    }

    @GetMapping("/teachers/school/{school}")
    public List<Staff> getAllStudentBySchool(@PathVariable String  school) {
        return teachersService.findAllBySchool(UUID.fromString(school));
    }

    @GetMapping("/teachers/{uuid}")
    public Staff getStudent(@PathVariable String uuid) throws UserNotFountException {
        return teachersService.findStaff(UUID.fromString(uuid));
    }

    @PostMapping("/teachers")
    public ResponseEntity<String> insertStaff(@Valid @RequestBody CreateStaff createStaff,
                                              @RequestHeader String userId) {
        return ResponseEntity.ok(teachersService.insertStaff( createStaff,userId));
    }

}
