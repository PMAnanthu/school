package com.school.academic.dto;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String batch;
    private String grade;
    private String division;
    private UUID inCharge;
    @ElementCollection
    private List<UUID> students;
    private boolean active;
}
