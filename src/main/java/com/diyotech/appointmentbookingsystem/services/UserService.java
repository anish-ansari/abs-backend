package com.diyotech.appointmentbookingsystem.services;

import com.diyotech.appointmentbookingsystem.dto.AppointmentDto;
import com.diyotech.appointmentbookingsystem.dto.LoginDto;
import com.diyotech.appointmentbookingsystem.dto.UserDto;
import com.diyotech.appointmentbookingsystem.entity.AppointmentEntity;
import com.diyotech.appointmentbookingsystem.entity.UserEntity;
import com.diyotech.appointmentbookingsystem.mapper.AppointmentMapper;
import com.diyotech.appointmentbookingsystem.mapper.LoginMapper;
import com.diyotech.appointmentbookingsystem.mapper.UserMapper;
import com.diyotech.appointmentbookingsystem.repository.AppointmentRepo;
import com.diyotech.appointmentbookingsystem.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo _userRepo;
    private final AppointmentRepo _appointmentRepo;
    public UserService(UserRepo userRepo, AppointmentRepo appointmentRepo) {
        _userRepo = userRepo;
        _appointmentRepo = appointmentRepo;
    }

    // SIGNUP:
    public String create(UserDto userDto) {
        // to check if a user already exists or not during sign up:
        userDto.setIsAdmin(false);
        UserEntity user = UserMapper.mapUserDtoToUserEntity(userDto);
        UserEntity validUser = _userRepo.findUserEntityByEmailAndUsername(user.getEmail(), user.getUsername());
        if (validUser != null) {
            return "User already exists";
        }
        return _userRepo.save(UserMapper.mapUserDtoToUserEntity(userDto)).getUserId() != null ? "success" : "failure";
    }

    // READ:
    public List<UserDto> read() {
        return _userRepo.findAll().stream()
                .map(UserMapper::mapUserEntityToUserDto)
                .collect(Collectors.toList());
    }

    // READBYID:
    public UserDto selectById(Long userId) {
        Optional<UserEntity> optionalUser = _userRepo.findById(userId);
        return UserMapper.mapUserEntityToUserDto(optionalUser.get());
    }

    // UPDATE:
    public String update(Long userId, UserDto userDto) {
        UserDto existingUser = selectById(userId);

        if (userDto.getFullName() != null)
            existingUser.setFullName(userDto.getFullName());
        if (userDto.getUsername() != null)
            existingUser.setUsername(userDto.getUsername());
        if (userDto.getPassword() != null)
            existingUser.setPassword(userDto.getPassword());
        if (userDto.getAddress() != null)
            existingUser.setAddress(userDto.getAddress());
        if (userDto.getEmail() != null)
            existingUser.setEmail(userDto.getEmail());
        if (userDto.getPhone() != null)
            existingUser.setPhone(userDto.getPhone());
        if (userDto.getIsAdmin() != null)
            existingUser.setIsAdmin(userDto.getIsAdmin());
        /*if (userDto.getAppointments() == null) {
            // used a native query to find value of joined table:
            AppointmentEntity existingAppointment = _appointmentRepo.runNativeQuery(userId);
            // converted Entity to Dto:
            AppointmentDto convertedAppointment = AppointmentMapper.mapAppointmentEntityToAppointmentDto(existingAppointment);
            // defined a list because entity & dto contain List field:
            Set<AppointmentDto> listAppointment = new HashSet<>();
            // adding the single entry or more into the list
            listAppointment.add(convertedAppointment);
            // adding the value from the list to the appointment for update
            existingUser.setAppointments(listAppointment);
        }*/

        _userRepo.save(UserMapper.mapUserDtoToUserEntity(existingUser));
        return "success";
    }

    // DELETE:
    public String delete(Long userId) {
        selectById(userId);
        _userRepo.deleteById(userId);
        return "success";
    }

    // LOGIN
    public LoginDto checkValidUser(LoginDto loginDto) {
        UserEntity user = LoginMapper.mapLoginDtoToUserEntity(loginDto);
        UserEntity validUser = _userRepo.findUserEntityByUsernameAndPassword(user.getUsername(), user.getPassword());
//        System.err.println(validUser);

        if (validUser != null)
            return LoginMapper.mapUserEntityToLoginDto(validUser);
        return null;
    }
}
