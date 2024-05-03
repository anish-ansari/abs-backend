package com.diyotech.appointmentbookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[user]")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fullName;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone;
//    @Column(columnDefinition = "boolean default false")
    private Boolean isAdmin;

    /*// One User can have Many Appointment:
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<AppointmentEntity> appointments = new HashSet<>();*/
}
