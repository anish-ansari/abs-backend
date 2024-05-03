package com.diyotech.appointmentbookingsystem.controllers;

import com.diyotech.appointmentbookingsystem.dto.AppointmentDto;
import com.diyotech.appointmentbookingsystem.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:58294/")
@RequestMapping("/abs/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService _appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        _appointmentService = appointmentService;
    }

    // CREATE:
    @PostMapping
    public ResponseEntity<String> create(@RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.ok(_appointmentService.create(appointmentDto));
    }

    // READ:
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> read() {
        return ResponseEntity.ok().body(_appointmentService.read());
    }

    // READBYID:
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> readById(@PathVariable("id") Long appointmentId) {
        return ResponseEntity.ok().body(_appointmentService.selectById(appointmentId));
    }

    // UPDATE:
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long appointmentId, @RequestBody AppointmentDto appointmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_appointmentService.update(appointmentId, appointmentDto));
    }

    // DELETE:
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long appointmentId) {
        return ResponseEntity.ok().body(_appointmentService.delete(appointmentId));
    }
}
