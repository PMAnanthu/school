package com.school.academic.http.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class DivisionRequest {
    private String uuid;
    @NotNull
    @NotEmpty
    private String batch;
    @NotNull
    @NotEmpty
    private String grade;
    @NotNull
    @NotEmpty
    private String division;
    private UUID inCharge;
    private List<UUID> students;
    private boolean active;
}
