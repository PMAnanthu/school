package com.school.academic.resources;

import com.school.academic.dto.Exam;
import com.school.academic.http.dto.ExamRequest;
import com.school.academic.service.ExamService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/exams")
public class ExamResources {
    private final ExamService service;

    @GetMapping()
    public List<Exam> getAllExam() {
        return service.findAll();
    }

    @GetMapping("/{uuid}")
    public Exam getExam(@PathVariable String uuid) {
        return service.findById(uuid);
    }

    @PostMapping()
    public String create(@RequestBody ExamRequest request, @RequestHeader String userId) {
        return service.create(request, userId);
    }

    @PutMapping()
    public String update(@RequestBody ExamRequest request, @RequestHeader String userId) {
        return service.update(request, userId);
    }
}
