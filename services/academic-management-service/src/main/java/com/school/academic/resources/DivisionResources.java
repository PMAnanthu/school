package com.school.academic.resources;

import com.school.academic.dto.Division;
import com.school.academic.http.dto.DivisionRequest;
import com.school.academic.http.dto.UserRequest;
import com.school.academic.service.DivisionService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/divisions")
public class DivisionResources {

    private final DivisionService service;

    @GetMapping()
    public List<Division> reads(){
        return service.findAll();
    }

    @GetMapping("/{division}")
    public Division read(@PathVariable String division){
        return service.findById(division);
    }

    @PostMapping()
    public String create(@RequestBody DivisionRequest request,
                         @RequestHeader String userId){
        return service.create(request,userId);
    }

    @PutMapping()
    public String update(@RequestBody DivisionRequest request,
                         @RequestHeader String userId){
        return service.update(request,userId);
    }

    @PutMapping("/add/student")
    public String addStudent(@RequestBody UserRequest request,
                             @RequestHeader String userId) {
        return service.addStudent(request,userId);
    }

    @PutMapping("/remove/student")
    public String removeStudent(@RequestBody UserRequest request,
                                @RequestHeader String userId){
        return service.removeStudent(request,userId);
    }

    @PutMapping("/update/teacher")
    public String updateInCharge(@RequestBody UserRequest request,
                                 @RequestHeader String userId){
        return service.updateTeacher(request,userId);
    }
}
