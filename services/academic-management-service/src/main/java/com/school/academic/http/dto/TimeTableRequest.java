package com.school.academic.http.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TimeTableRequest {
    private String uuid;
    @NotNull
    @NotEmpty
    private String division;
    @NotNull
    @NotEmpty
    private String subject;
    @NotNull
    @NotEmpty
    private String teacher;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    @NotEmpty
    private String url;
}
