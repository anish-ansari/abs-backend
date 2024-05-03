package com.diyotech.appointmentbookingsystem.mapper;

import com.diyotech.appointmentbookingsystem.dto.LoginDto;
import com.diyotech.appointmentbookingsystem.entity.UserEntity;

public class LoginMapper {
    // UserEntity -> LoginDto
    public static LoginDto mapUserEntityToLoginDto(UserEntity user) {
        return LoginDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    // LoginDto -> UserEntity
    public static UserEntity mapLoginDtoToUserEntity(LoginDto loginDto) {
        return UserEntity.builder()
                .userId(loginDto.getUserId())
                .username(loginDto.getUsername())
                .password(loginDto.getPassword())
                .build();
    }
}
