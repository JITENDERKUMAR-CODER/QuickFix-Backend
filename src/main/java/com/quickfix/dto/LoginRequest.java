package com.quickfix.dto;

import com.quickfix.entity.User;
import lombok.Data;

import java.util.Optional;

@Data
public class LoginRequest {

    private String email;

    private String password;

    Optional<User> findByEmail(String email) {
        return null;
    }

}