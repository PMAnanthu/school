package com.school.academic.http.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ScoreRequest {
    private String uuid;
    @NotEmpty
    @NotNull
    private String exam;
    @NotEmpty
    @NotNull
    private String student;
    @NotNull
    private Double score;
    @NotEmpty
    @NotNull
    private String status;
    @NotEmpty
    @NotNull
    private String grade;
}
