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
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String mobileNumber;
    private String address;
    private boolean active;
}
