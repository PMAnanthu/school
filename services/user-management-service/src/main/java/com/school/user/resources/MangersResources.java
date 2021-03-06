/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 26/06/21
*/
package com.school.user.resources;

import com.school.user.dto.Staff;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateManger;
import com.school.user.service.MangersService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
@RestController
public class MangersResources {

    private final MangersService mangersService;

    @GetMapping("/mangers")
    public List<Staff> getAllMangers() {
        return mangersService.findAll();
    }

    @GetMapping("/mangers/school/{school}")
    public List<Staff> getAllMangersBySchool(@PathVariable String  school) {
        return mangersService.findAllBySchool(UUID.fromString(school));
    }

    @GetMapping("/mangers/{uuid}")
    public Staff getMangers(@PathVariable String uuid) throws UserNotFountException {
        return mangersService.findStaff(UUID.fromString(uuid));
    }

    @PostMapping("/mangers")
    public ResponseEntity<String> insertMangers(@Valid @RequestBody CreateManger createManger,
                                                @RequestHeader String userId) {
        return ResponseEntity.ok(mangersService.insertManger(createManger,userId));
    }

}
