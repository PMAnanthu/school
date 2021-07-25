/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.studentmanagementservice.resources;

import com.school.studentmanagementservice.http.dto.CreateStudent;
import com.school.studentmanagementservice.service.StudentService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Data
@RestController
public class StudentResources {

    private final StudentService studentService;

    @GetMapping("/students")
    public String insertStudent() {
        return "Success";
    }

    @PostMapping("/students")
    public ResponseEntity<String> insertStudent(@Valid @RequestBody CreateStudent createStudent) {
        return ResponseEntity.ok(studentService.insertStudent( createStudent));

    }

}
