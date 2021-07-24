package com.school.studentmanagementservice.proxy;

import com.school.studentmanagementservice.http.dto.CreateStudentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="student-management-service")
public interface StudentServiceProxy {
    @PostMapping("/students")
    public String createLogin(@RequestBody CreateStudentRequest request);
}
