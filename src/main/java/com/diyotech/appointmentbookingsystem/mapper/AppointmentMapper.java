package com.diyotech.appointmentbookingsystem.mapper;

import com.diyotech.appointmentbookingsystem.dto.AppointmentDto;
import com.diyotech.appointmentbookingsystem.dto.UserDto;
import com.diyotech.appointmentbookingsystem.entity.AppointmentEntity;
import com.diyotech.appointmentbookingsystem.entity.UserEntity;

public class AppointmentMapper {
    // AppointmentEntity -> AppointmentDto
    public static AppointmentDto mapAppointmentEntityToAppointmentDto(AppointmentEntity appointment) {
        return AppointmentDto.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentDateTime(appointment.getAppointmentDateTime())
                .officeLocation(appointment.getOfficeLocation())
                .serviceType(appointment.getServiceType())
                .status(appointment.getStatus())
                .userId(appointment.getUser().getUserId())
                .build();
    }

    // AppointmentDto -> AppointmentEntity
    public static AppointmentEntity mapAppointmentDtoToAppointmentEntity(AppointmentDto appointmentDto) {
        return AppointmentEntity.builder()
                .appointmentId(appointmentDto.getAppointmentId())
                .appointmentDateTime(appointmentDto.getAppointmentDateTime())
                .officeLocation(appointmentDto.getOfficeLocation())
                .serviceType(appointmentDto.getServiceType())
                .status(appointmentDto.getStatus())
//                .user(appointmentDto.getUserDto())
                .user(UserEntity.builder()
                        .userId(appointmentDto.getUserId())
                        .build())
//                .customer(Customer.builder().customerId(paymentInfoDto.getCustomerId()).build())
                .build();
    }
}
