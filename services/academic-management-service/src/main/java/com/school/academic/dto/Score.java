package com.school.academic.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private UUID exam;
    private UUID student;
    private Double score;
    private String status;
    private String grade;
}
