package com.diyotech.appointmentbookingsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy'T'HH:mm")
    private LocalDateTime appointmentDateTime;

    private String officeLocation;
    private String serviceType;
    private AppointmentStatus status;

    // Many Appointment can have One User:
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // do this to enable cascading for many to one relationship
    @JsonIgnore
    private UserEntity user;
}
