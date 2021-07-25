/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.user.resources;

import com.school.user.dto.Student;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateStudent;
import com.school.user.service.StudentService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
@RestController
public class StudentResources {

    private final StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }

    @GetMapping("/students/school/{school}")
    public List<Student> getAllStudentBySchool(@PathVariable String  school) {
        return studentService.findAllBySchool(UUID.fromString(school));
    }

    @GetMapping("/students/{uuid}")
    public Student getStudent(@PathVariable String uuid) throws UserNotFountException {
        return studentService.findStudent(UUID.fromString(uuid));
    }

    @PostMapping("/students")
    public ResponseEntity<String> insertStudent(@Valid @RequestBody CreateStudent createStudent,
                                                @RequestHeader String school) {
        return ResponseEntity.ok(studentService.insertStudent( createStudent,school));
    }

}
