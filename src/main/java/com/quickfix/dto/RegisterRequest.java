package com.quickfix.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String fullName;

    private String email;

    private String phone;

    private String password;
    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

}
