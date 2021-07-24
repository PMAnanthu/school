package com.school.studentmanagementservice.http.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStudentResponse {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
    private String gender;
    private Boolean onlyFemaleChild;
    private String address;
    private String email;
    private String admissionFor;
    private LocalDate dateOfAdmission;
    private String admissionNumber;
    private String parent;
    private String castDetails;
    private String religion;
    private Integer bloodGroup;
    private Boolean active;
}
