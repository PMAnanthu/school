/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.studentmanagementservice.resources;

import com.school.studentmanagementservice.exception.NotValidInputException;
import com.school.studentmanagementservice.exception.UserNotFountException;
import com.school.studentmanagementservice.requests.CreateStudent;
import com.school.studentmanagementservice.requests.LoadStudent;
import com.school.studentmanagementservice.requests.UpdateStudent;
import com.school.studentmanagementservice.responses.StudentResponse;
import com.school.studentmanagementservice.service.StudentService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Data
@RestController
public class StudentResources {

    private final StudentService studentService;

    @GetMapping()
    public List<StudentResponse> getAllStudent() {
        return studentService.getAllStudent();
    }


    @GetMapping("/students/{id}")
    public StudentResponse getStudent(@PathVariable String id) throws UserNotFountException {
        return studentService.getStudent(id);
    }


    @GetMapping(path = "/students/filter")
    public List<StudentResponse> getAllStudentByFilter(@RequestParam String field,
                                                       @RequestParam String value)
            throws NotValidInputException {
        return studentService.getAllStudentByFilter(field, value);
    }

    @PostMapping("/students")
    public ResponseEntity<String> insertStudent(@Valid @RequestBody CreateStudent createStudent) {
        return ResponseEntity.ok(studentService.insertStudent( createStudent));

    }

    @PostMapping( "/students/load")
    public ResponseEntity<String> loadStudents(@Valid @RequestBody LoadStudent loadStudent) {
        return ResponseEntity.ok(studentService.loadStudents(loadStudent));
    }

    @PutMapping("/students/")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody UpdateStudent updateStudent) {
        return ResponseEntity.ok(studentService.updateStudent(updateStudent));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable String id,
                                               @RequestParam boolean status) throws UserNotFountException {
        return ResponseEntity.ok(studentService.updateStatus(id, status));
    }
}
