/*
Copyright @ 2021 - covist
Project : skyhawks-core
Written: ananthupm
Date : 30/06/21
*/
package com.school.user.http.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CreateStudent {
    @NotEmpty(message = "User name should not be empty")
    @NotNull(message = "User name should not be null")
    private String userName;
    @NotEmpty(message = "First name should not be empty")
    @NotNull(message = "First name should not be null")
    private String firstName;
    private String lastName;
    private String middleName;
    @NotNull(message = "Date of birth  should not be null")
    private LocalDate dateOfBirth;
    @NotEmpty(message = "Gender should not be empty")
    @NotNull(message = "Gender should not be null")
    private String gender;
    private Boolean onlyFemaleChild;
    private String address;
    @NotEmpty(message = "Email should not be empty")
    @NotNull(message = "Email should not be null")
    private String email;
    private String admissionFor;
    private LocalDate dateOfAdmission;
    @NotEmpty(message = "Admission number should not be empty")
    @NotNull(message = "Admission number should not be null")
    private String admissionNumber;
    private String parent;
    private String castDetails;
    private String religion;
    private Integer bloodGroup;
}
