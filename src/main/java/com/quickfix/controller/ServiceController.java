package com.quickfix.controller;

import com.quickfix.dto.ServiceRequest;
import com.quickfix.dto.ServiceResponse;
import com.quickfix.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public String createService(@RequestBody ServiceRequest request) {

        return serviceService.createService(request);
    }
    @GetMapping
    public List<ServiceResponse> getAllServices() {
        return serviceService.getAllServices();
    }
    @GetMapping("/category/{categoryId}")
    public List<ServiceResponse> getServicesByCategory(
            @PathVariable Long categoryId) {

        return serviceService.getServicesByCategory(categoryId);

    }
    @PutMapping("/{id}")
    public String updateService(@PathVariable Long id, @RequestBody ServiceRequest request) {
        return serviceService.updateService(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Long id) {
        return serviceService.deleteService(id);
    }

}