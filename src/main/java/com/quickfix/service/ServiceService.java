package com.quickfix.service;

import com.quickfix.dto.ServiceRequest;
import com.quickfix.entity.Category;
import com.quickfix.repository.CategoryRepository;
import com.quickfix.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.quickfix.entity.Service;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public String createService(ServiceRequest request) {
        // Step 1: Find Category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category Not Found"));
        // Step 2: Create Service
        Service service = new Service();
        service.setCategory(category);


        // Step 3: Map Request → Entity
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setEstimatedDuration(request.getEstimatedDuration());

        // Step 4: Set Category
        service.setCategory(category);

        // Step 5: Set Default Values
        service.setActive(true);
        service.setCreatedAt(LocalDateTime.now());
        service.setUpdatedAt(LocalDateTime.now());

        // Step 6: Save
        serviceRepository.save(service);

        // Step 7: Return
        return "Service Created Successfully";

    }
}