/*
Copyright @ 2021
Project : skyhawks-core
Written: ananthupm
Date : 30/06/21
*/
package com.school.studentmanagementservice.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateStudent {

    @NotEmpty(message = "Admission number should not be empty")
    @NotNull(message = "Admission number should not be null")
    private String admissionNumber;

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
    private String parent;
    private String castDetails;
    private String religion;
    private Integer bloodGroup;
}
