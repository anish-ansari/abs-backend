package com.diyotech.appointmentbookingsystem.mapper;

import com.diyotech.appointmentbookingsystem.dto.UserDto;
import com.diyotech.appointmentbookingsystem.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
    // UserEntity -> UserDto for READ & UPDATE
    public static UserDto mapUserEntityToUserDto(UserEntity user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .password(user.getPassword())
                .address(user.getAddress())
                .email(user.getEmail())
                .phone(user.getPhone())
                .isAdmin(user.getIsAdmin())
                .build();
    }

    // UserDto -> UserEntity for CREATE & DELETE
    public static UserEntity mapUserDtoToUserEntity(UserDto userDtoForInsertAndDelete) {
        return UserEntity.builder()
                .userId(userDtoForInsertAndDelete.getUserId())
                .fullName(userDtoForInsertAndDelete.getFullName())
                .username(userDtoForInsertAndDelete.getUsername())
                .password(userDtoForInsertAndDelete.getPassword())
                .address(userDtoForInsertAndDelete.getAddress())
                .email(userDtoForInsertAndDelete.getEmail())
                .phone(userDtoForInsertAndDelete.getPhone())
                .isAdmin(userDtoForInsertAndDelete.getIsAdmin())
                /*.appointments(userDtoForInsertAndDelete.getAppointments().stream()
                        .map(AppointmentMapper::mapAppointmentDtoToAppointmentEntity)
                        .collect(Collectors.toSet()))*/
                .build();
    }
}
