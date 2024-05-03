package com.diyotech.appointmentbookingsystem.services;

import com.diyotech.appointmentbookingsystem.dto.AppointmentDto;
import com.diyotech.appointmentbookingsystem.entity.AppointmentEntity;
import com.diyotech.appointmentbookingsystem.mapper.AppointmentMapper;
import com.diyotech.appointmentbookingsystem.repository.AppointmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final AppointmentRepo _appointmentRepo;

    public AppointmentService(AppointmentRepo appointmentRepo) {
        _appointmentRepo = appointmentRepo;
    }

    // CREATE:
    public String create(AppointmentDto appointmentDto) {
        // to check if there is a duplicate appointment being created:
        AppointmentEntity appointment = AppointmentMapper.mapAppointmentDtoToAppointmentEntity(appointmentDto);
        AppointmentEntity existingAppointment = _appointmentRepo.runNativeQuery(appointmentDto.getUserId(),
                appointment.getAppointmentDateTime(), appointment.getOfficeLocation(), appointment.getServiceType(),
                appointment.getStatus());

        if (existingAppointment != null)
            return "Appointment already exists";
        return _appointmentRepo.save(AppointmentMapper.mapAppointmentDtoToAppointmentEntity(appointmentDto))
                .getAppointmentId() != null ? "success" : "failure";
    }

    // READ:
    public List<AppointmentDto> read() {
        return _appointmentRepo.findAll().stream()
                .map(AppointmentMapper::mapAppointmentEntityToAppointmentDto)
                .collect(Collectors.toList());
    }

    // READBYID:
    public AppointmentDto selectById(Long appointmentId) {
        Optional<AppointmentEntity> optionalAppointment = _appointmentRepo.findById(appointmentId);
        return AppointmentMapper.mapAppointmentEntityToAppointmentDto(optionalAppointment.get());
    }

    // UPDATE:
    public String update(Long appointmentId, AppointmentDto appointmentDto) {
        AppointmentDto existingAppointment = selectById(appointmentId);

        if (appointmentDto.getAppointmentDateTime() != null)
            existingAppointment.setAppointmentDateTime(appointmentDto.getAppointmentDateTime());
        if (appointmentDto.getOfficeLocation() != null)
            existingAppointment.setOfficeLocation(appointmentDto.getOfficeLocation());
        if (appointmentDto.getServiceType() != null)
            existingAppointment.setServiceType(appointmentDto.getServiceType());
        if (appointmentDto.getStatus() != null)
            existingAppointment.setStatus(appointmentDto.getStatus());

        _appointmentRepo.save(AppointmentMapper.mapAppointmentDtoToAppointmentEntity(existingAppointment));
        return "success";
    }

    // DELETE:
    public String delete(Long appointmentId) {
        selectById(appointmentId);
        _appointmentRepo.deleteById(appointmentId);
        return "success";
    }
}
