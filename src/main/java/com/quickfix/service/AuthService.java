package com.quickfix.service;

import com.quickfix.dto.LoginRequest;
import com.quickfix.dto.RegisterRequest;
import com.quickfix.entity.User;
import com.quickfix.entity.enums.UserRole;
import com.quickfix.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    public String register(RegisterRequest register) {
//        check email
        if(userRepository.existsByEmail(register.getEmail())) {
            return "Email already exists";
        }
//        check phone
        if(userRepository.existsByPhone(register.getPhone())) {
            return "Phone already exists";
        }

//        create User object
        User user = new User();
//        copy data from DTO
        user.setEmail(register.getEmail());
        user.setPhone(register.getPhone());
        user.setPassword( passwordEncoder.encode(register.getPassword()));
        user.setFullName(register.getFullName());
        user.setRole(UserRole.CUSTOMER);
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        //        save into database
        userRepository.save(user);
//        Return response
        return "Register successful";
    }
    public String login(LoginRequest request) {

      Optional<User>  user = userRepository.findByEmail(request.getEmail());
        if (user.isEmpty()) {
            return "Invalid Email";
        }
        User foundUser = user.get();
        if (!passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
            return "Invalid Password";
        }



        String token = jwtService.generateToken(foundUser.getEmail());

        return token;
    }


}