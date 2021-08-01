package com.school.academic.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private UUID division;
    private String subject;
    private UUID teacher;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String url;
}
