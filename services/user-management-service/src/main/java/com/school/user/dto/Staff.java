package com.school.user.dto;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@Table
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(name = "userId", unique = true)
    private String userId;
    private String firstName;
    private String lastName;
    private String middleName;
    @Column(name = "email", unique = true)
    private String email;
    private String mobileNumber;
    private String address;
    private boolean active;
}
