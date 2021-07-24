/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 30/06/21
*/
package com.school.studentmanagementservice.service;

import com.school.studentmanagementservice.dto.UserMapping;
import com.school.studentmanagementservice.http.dto.CreateLoginRequest;
import com.school.studentmanagementservice.http.dto.CreateStudent;
import com.school.studentmanagementservice.http.dto.CreateStudentRequest;
import com.school.studentmanagementservice.http.dto.StudentResponse;
import com.school.studentmanagementservice.proxy.AuthProxy;
import com.school.studentmanagementservice.proxy.StudentServiceProxy;
import com.school.studentmanagementservice.repo.IUserMappingRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Service
public class StudentService {

    private final AuthProxy authProxy;
    private final StudentServiceProxy studentServiceProxy;
    private final IUserMappingRepo iUserMappingRepo;
    private final ModelMapper modelMapper;

    public String insertStudent(CreateStudent createStudent) {
        UserMapping userMapping=new UserMapping();
        try {
            CreateLoginRequest createLoginRequestRequest=new CreateLoginRequest();
            createLoginRequestRequest.setEmail(createStudent.getEmail());
            createLoginRequestRequest.setName(createStudent.getFirstName()+" "+createStudent.getMiddleName()!=null?
                    createStudent.getMiddleName():""+" "+
                            createStudent.getLastName()!=null?createStudent.getLastName():"");
            createLoginRequestRequest.setUserName(createStudent.getUserName());
            createLoginRequestRequest.setPassword("Password");
            String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
            if(createLoginResponse!=null){
                userMapping.setLogin(UUID.fromString(createLoginResponse));
            }
        }catch (Exception e){

        }
        try{
            CreateStudentRequest createStudentRequest=modelMapper.map(createStudent,CreateStudentRequest.class);
            String studentResponse= studentServiceProxy.createLogin(createStudentRequest);
            if(studentServiceProxy!=null){
                userMapping.setUserId(UUID.fromString(studentResponse));
            }
        }catch (Exception e){

        }
        userMapping.setUserType("student");
        userMapping.setSchool(UUID.fromString(createStudent.getSchool()));
        if(iUserMappingRepo.save(userMapping)!=null) {
            return "Student Created";
        }else {
            return null;
        }
    }

}
