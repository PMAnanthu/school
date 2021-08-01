package com.school.academic.resources;

import com.school.academic.dto.TimeTable;
import com.school.academic.http.dto.TimeTableRequest;
import com.school.academic.service.TimeTableService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/time-tables")
public class TimeTableResources {
    private final TimeTableService service;

    @GetMapping()
    public List<TimeTable> getAll() {
        return service.findAll();
    }

    @GetMapping("/{uuid}")
    public TimeTable getExam(@PathVariable String uuid) {
        return service.findById(uuid);
    }

    @PostMapping()
    public String create(@RequestBody TimeTableRequest request, @RequestHeader String userId) {
        return service.create(request, userId);
    }

    @PutMapping()
    public String update(@RequestBody TimeTableRequest request, @RequestHeader String userId) {
        return service.update(request, userId);
    }
}
