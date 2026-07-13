package com.quickfix.controller;

import com.quickfix.dto.WorkerProfileRequest;
import com.quickfix.dto.WorkerProfileResponse;
import com.quickfix.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("/profile")
    public String createWorkerProfile(
            @RequestBody WorkerProfileRequest request) {

        return workerService.createWorkerProfile(request);
    }
    @GetMapping("/me")
    public WorkerProfileResponse getMyProfile() {
        return workerService.getMyProfile();
    }
    @PutMapping("/profile")
    public String updateWorkerProfile(
            @RequestBody WorkerProfileRequest request) {

        return workerService.updateWorkerProfile(request);
    }
}