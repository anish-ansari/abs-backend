package com.diyotech.appointmentbookingsystem.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String fullName;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone;
    private Boolean isAdmin;
//    private Set<AppointmentDto> appointments;
}
