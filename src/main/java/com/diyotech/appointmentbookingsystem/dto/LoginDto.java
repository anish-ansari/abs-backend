package com.diyotech.appointmentbookingsystem.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private Long userId;
    private String username;
    private String password;
}
