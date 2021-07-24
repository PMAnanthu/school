package com.school.studentmanagementservice.http.dto;/*
Copyright @ 2021 - covist
Project : skyhawks-core
Written: ananthupm
Date : 30/06/21
*/

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class StudentResponse {
    private UUID uuid;
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
