package com.diyotech.appointmentbookingsystem.controllers;

import com.diyotech.appointmentbookingsystem.dto.LoginDto;
import com.diyotech.appointmentbookingsystem.dto.UserDto;
import com.diyotech.appointmentbookingsystem.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:58294/")
@RequestMapping("/abs/api/v1/users")
public class UserController {
    private final UserService _userService;
    public UserController(UserService userService) {
        _userService = userService;
    }

    // SIGNUP:
    @PostMapping("/signup")
    public ResponseEntity<String> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(_userService.create(userDto));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> checkValidUser(@RequestBody LoginDto loginDto) {
        System.err.println("Checking valid user....");
        LoginDto user = _userService.checkValidUser(loginDto);
        if (user == null)
            return ResponseEntity.status(200).body("User doesn't exist");
        return ResponseEntity.ok(user);
    }

    // READ:
    @GetMapping
    public ResponseEntity<List<UserDto>> read() {
        return ResponseEntity.ok().body(_userService.read());
    }

    // READBYID:
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok().body(_userService.selectById(userId));
    }

    // UPDATE:
    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long userId, @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(_userService.update(userId, userDto));
    }

    // DELETE:
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long userId) {
        return ResponseEntity.ok().body(_userService.delete(userId));
    }
}
