/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.studentmanagementservice.resources;

import com.school.studentmanagementservice.dto.Staff;
import com.school.studentmanagementservice.dto.Student;
import com.school.studentmanagementservice.exception.UserNotFountException;
import com.school.studentmanagementservice.http.dto.CreateStaff;
import com.school.studentmanagementservice.http.dto.CreateStudent;
import com.school.studentmanagementservice.service.StudentService;
import com.school.studentmanagementservice.service.TeachersService;
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
    public ResponseEntity<String> insertStaff(@Valid @RequestBody CreateStaff createStaff) {
        return ResponseEntity.ok(teachersService.insertStaff( createStaff));
    }

}
