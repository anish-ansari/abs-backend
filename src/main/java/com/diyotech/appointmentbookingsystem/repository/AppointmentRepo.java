package com.diyotech.appointmentbookingsystem.repository;

import com.diyotech.appointmentbookingsystem.entity.AppointmentEntity;
import com.diyotech.appointmentbookingsystem.entity.AppointmentStatus;
import com.diyotech.appointmentbookingsystem.mapper.AppointmentMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepo extends JpaRepository<AppointmentEntity, Long> {

    /*@Query(value = "select * from appointment where user_id = :userId", nativeQuery = true)
    AppointmentEntity runNativeQuery(Long userId);*/

    @Query(value = "select * from appointment where user_id = :userId and " +
            "appointment_date_time = :appointmentDateTime and office_location = :officeLocation and " +
            "service_type = :serviceType and status = :status", nativeQuery = true)
    AppointmentEntity runNativeQuery(Long userId, LocalDateTime appointmentDateTime,
                                     String officeLocation, String serviceType, AppointmentStatus status);
}
