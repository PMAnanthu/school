package com.school.academic.resources;

import com.school.academic.dto.Score;
import com.school.academic.http.dto.ScoreRequest;
import com.school.academic.service.ScoreService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/scores")
public class ScoreResources {
    private final ScoreService service;

    @GetMapping()
    public List<Score> getAll() {
        return service.findAll();
    }

    @GetMapping("/{uuid}")
    public Score getExam(@PathVariable String uuid) {
        return service.findById(uuid);
    }

    @PostMapping()
    public String create(@RequestBody ScoreRequest request, @RequestHeader String userId) {
        return service.create(request, userId);
    }

    @PutMapping()
    public String update(@RequestBody ScoreRequest request, @RequestHeader String userId) {
        return service.update(request, userId);
    }
}
