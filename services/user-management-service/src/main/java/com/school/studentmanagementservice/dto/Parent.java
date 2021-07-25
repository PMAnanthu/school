package com.school.studentmanagementservice.dto;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String address;
    private String mobile;
}
