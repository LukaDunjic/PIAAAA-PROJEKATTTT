package com.example.demo.owner;

import com.example.demo.user.model.RegistrationStatus;
import com.example.demo.user.model.UserEntity;
import lombok.Data;

@Data
public class UserWithStatusDto {
    UserEntity user;
    RegistrationStatus status;
}
