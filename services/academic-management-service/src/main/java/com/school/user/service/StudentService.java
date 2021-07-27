/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 30/06/21
*/
package com.school.user.service;

import com.school.user.dto.Student;
import com.school.user.dto.UserMapping;
import com.school.user.dto.UserType;
import com.school.user.exception.UserNotFountException;
import com.school.user.http.dto.CreateLoginRequest;
import com.school.user.http.dto.CreateStudent;
import com.school.user.proxy.AuthProxy;
import com.school.user.repo.IStudentRepo;
import com.school.user.repo.IUserMappingRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
public class StudentService {

    private final AuthProxy authProxy;
    private final IUserMappingRepo iUserMappingRepo;
    private final ModelMapper modelMapper;
    private final IStudentRepo iStudentRepo;
    private final Utils utils;
    private final SchoolService schoolService;

    public String insertStudent(CreateStudent createStudent, String userId) {
        try {
            Student student = iStudentRepo.save(modelMapper.map(createStudent, Student.class));
            if (student != null) {
                CreateLoginRequest createLoginRequestRequest = new CreateLoginRequest();
                createLoginRequestRequest.setEmail(createStudent.getEmail());
                String name=student.getFirstName();
                name=name+(student.getMiddleName()!=null?" "+student.getMiddleName():"");
                name=name+(student.getLastName()!=null?" "+student.getLastName():"");
                createLoginRequestRequest.setName(name);
                createLoginRequestRequest.setUserName(createStudent.getUserName());
                createLoginRequestRequest.setPassword(utils.randomChar(8));
                String createLoginResponse = authProxy.createLogin(createLoginRequestRequest);
                if(createLoginResponse!=null){
                    UserMapping userMapping = new UserMapping();
                    userMapping.setUserType(UserType.STUDENT);
                    userMapping.setLogin(UUID.fromString(createLoginResponse));
                    userMapping.setUserId(student.getUuid());
                    userMapping.setSchool(schoolService.findSchoolForUser(userId));
                    iUserMappingRepo.save(userMapping);
                    return createLoginResponse;
                }else {
                    iStudentRepo.delete(student);
                }
            }
            return "Unable to create login";
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Student> findAll() {
        List<Student> students=new ArrayList<>();
        iStudentRepo.findAll().iterator().forEachRemaining(student -> students.add(student));
        return students;
    }

    public Student findStudent(UUID uuid) throws UserNotFountException {
        Student student=iStudentRepo.findByUserId(uuid);
        if(student==null)
            throw  new UserNotFountException("Student Not Found with for ID "+uuid.toString());
        return student;
    }

    public List<Student> findAllBySchool(UUID school) {
        List<UserMapping> studentsMap= iUserMappingRepo.findAllStudentBySchoolAndType(school,UserType.STUDENT);
        Iterable<UUID> iterable =studentsMap.stream().map(UserMapping::getUserId).collect(Collectors.toList());
        Iterable<Student> iStudents= iStudentRepo.findAllById(iterable);
        List<Student> students=new ArrayList<>();
        iStudents.iterator().forEachRemaining(student -> students.add(student));
        return students;
    }
}
