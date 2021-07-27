package com.school.user.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String name;
    private String email;
    private String address;
    private String mobile;
    private String webSite;
}
