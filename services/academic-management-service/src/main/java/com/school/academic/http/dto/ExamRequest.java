package com.school.academic.http.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ExamRequest {
    private String uuid;
    @NotNull
    @NotEmpty
    private String division;
    @NotNull
    private LocalDateTime scheduledTime;
    @NotNull
    @NotEmpty
    private String subject;
    @NotNull
    @NotEmpty
    private String term;
    @NotNull
    private Double outOff;
    @NotNull
    private Integer year;
}
