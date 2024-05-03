package com.diyotech.appointmentbookingsystem.repository;

import com.diyotech.appointmentbookingsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    public UserEntity findUserEntityByEmailAndUsername(String email, String username);
    public UserEntity findUserEntityByUsernameAndPassword(String username, String password);
}
