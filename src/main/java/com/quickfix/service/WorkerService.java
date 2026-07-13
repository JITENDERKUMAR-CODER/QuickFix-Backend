package com.quickfix.service;

import com.quickfix.dto.WorkerProfileRequest;
import com.quickfix.dto.WorkerProfileResponse;
import com.quickfix.entity.User;
import com.quickfix.entity.WorkerProfile;
import com.quickfix.entity.enums.UserRole;
import com.quickfix.repository.UserRepository;
import com.quickfix.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private UserRepository userRepository;
    public String createWorkerProfile(WorkerProfileRequest request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();


        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        if (workerRepository.findByUser(user).isPresent()) {
            return "Worker Profile Already Exists";
        }
        WorkerProfile worker = new WorkerProfile();
        worker.setExperience(request.getExperience());
        worker.setBio(request.getBio());
        worker.setCity(request.getCity());
        worker.setHourlyRate(request.getHourlyRate());
        worker.setUser(user);
        user.setRole(UserRole.WORKER);
        userRepository.save(user);
        worker.setVerified(false);
        worker.setRating(0.0);
        worker.setCreatedAt(LocalDateTime.now());
        worker.setUpdatedAt(LocalDateTime.now());
        workerRepository.save(worker);
        return "Worker Profile Created Successfully";
    }
    public WorkerProfileResponse getMyProfile() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));
        WorkerProfile worker = workerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Worker Profile Not Found"));
        return new WorkerProfileResponse(
                worker.getId(),
                worker.getUser().getFullName(),
                worker.getUser().getEmail(),
                worker.getExperience(),
                worker.getBio(),
                worker.getCity(),
                worker.getHourlyRate(),
                worker.getVerified(),
                worker.getRating()
        );

    }
    public String updateWorkerProfile(WorkerProfileRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        WorkerProfile worker = workerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Worker Profile Not Found"));

        worker.setExperience(request.getExperience());
        worker.setBio(request.getBio());
        worker.setCity(request.getCity());
        worker.setHourlyRate(request.getHourlyRate());
        worker.setUpdatedAt(LocalDateTime.now());

        workerRepository.save(worker);

        return "Worker Profile Updated Successfully";
    }
}