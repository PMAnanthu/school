/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 30/06/21
*/
package com.school.studentmanagementservice.service;

import com.school.studentmanagementservice.TimeUtils;
import com.school.studentmanagementservice.Utils;
import com.school.studentmanagementservice.dto.Student;
import com.school.studentmanagementservice.exception.NotValidInputException;
import com.school.studentmanagementservice.exception.UserNotFountException;
import com.school.studentmanagementservice.repo.IStudentRepo;
import com.school.studentmanagementservice.requests.CreateStudent;
import com.school.studentmanagementservice.requests.LoadStudent;
import com.school.studentmanagementservice.requests.UpdateStudent;
import com.school.studentmanagementservice.responses.StudentResponse;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Service
public class StudentService {
    private final IStudentRepo iStudentRepo;
    private final ModelMapper modelMapper;

    public StudentResponse getStudent(String id)
            throws UserNotFountException {
        Student student = iStudentRepo.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFountException(String.format(
                "Unable " +
                        "to find the user with %s as %s ", "id", id)));
        return modelMapper.map(student, StudentResponse.class);
    }


    public List<StudentResponse> getAllStudent() {
        return iStudentRepo
                .findAll()
                .stream()
                .map(val -> modelMapper.map(val, StudentResponse.class))
                .collect(Collectors.toList());
    }

    public String insertStudent(CreateStudent createStudent) {
        Student student = modelMapper.map(createStudent, Student.class);
        student.setActive(true);
        student = iStudentRepo.save(student);
        if (student.getUuid() != null) {
            return student.getUuid().toString();
        }
        return null;
    }


    public String loadStudents(LoadStudent loadStudent) {
        //TODO
        return null;
    }

    public String updateStudent(UpdateStudent updateStudent) {
        Student student = modelMapper.map(updateStudent, Student.class);
        UUID uuid = iStudentRepo.findIDByAdmissionNumber(updateStudent.getAdmissionNumber()).getUuid();
        student.setUuid(uuid);
        iStudentRepo.save(student);
        return "Student updated successfully";
    }

    public String updateStatus(String id, boolean status) throws UserNotFountException {
        Student student = iStudentRepo.findById(UUID.fromString(id)).orElseThrow(()->new UserNotFountException(String.format(
                "Unable " +
                        "to find the user with %s as %s ", "id", id)));
        student.setActive(status);
        iStudentRepo.save(student);
        return "Student disabled successfully";
    }

    public List<StudentResponse> getAllStudentByFilter(String field, String value) throws NotValidInputException {
        Utils.basicInputValidation(field);
        Utils.basicInputValidation(value);
        List<Student> list;
        switch (field.toLowerCase()) {
            case "firstname":
                list = iStudentRepo.findByFirstName(value);
                break;
            case "lastname":
                list = iStudentRepo.findByLastName(value);
                break;
            case "middlename":
                list = iStudentRepo.findByMiddleName(value);
                break;
            case "dateofbirth":
                list = iStudentRepo.findDateOfBirth(TimeUtils.getDate(value));
                break;
            case "onlyFemaleChild":
                list = iStudentRepo.findOnlyFemaleChild(Utils.getBoolean(value));
                break;
            case "dateOfAdmission":
                list = iStudentRepo.findDateOfAdmission(TimeUtils.getDate(value));
                break;
            case "parent":
                list = iStudentRepo.findByParent(UUID.fromString(value));
                break;
            case "bloodGroup":
                list = iStudentRepo.findByBloodGroup(1);
                break;
            default:
                throw new NotValidInputException(String.format("%s not supported", field));
        }
        return list.stream().map(val -> modelMapper.map(val, StudentResponse.class)).collect(Collectors.toList());

    }
}
