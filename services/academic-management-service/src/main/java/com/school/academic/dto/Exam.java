package com.school.academic.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private UUID division;
    private String term;
    private LocalDateTime scheduledTime;
    private String subject;
    private Double outOff;
}
