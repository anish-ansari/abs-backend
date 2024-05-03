package com.diyotech.appointmentbookingsystem.dto;

import com.diyotech.appointmentbookingsystem.entity.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class AppointmentDto {
    private Long appointmentId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy'T'HH:mm")
    private LocalDateTime appointmentDateTime;

    private String officeLocation;
    private String serviceType;
    private AppointmentStatus status;
    private Long userId;
}
