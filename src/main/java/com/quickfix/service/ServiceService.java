package com.quickfix.service;

import com.quickfix.dto.ServiceRequest;
import com.quickfix.dto.ServiceResponse;
import com.quickfix.entity.Category;
import com.quickfix.repository.CategoryRepository;
import com.quickfix.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.quickfix.entity.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
    public List<ServiceResponse> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return services.stream()
                .map(service -> new ServiceResponse(
                        service.getId(),
                        service.getName(),
                        service.getDescription(),
                        service.getPrice(),
                        service.getEstimatedDuration(),
                        service.getCategory().getName()

                ))
                .toList();

    }
    public ServiceResponse getServiceById(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Service Not Found"));
        service.getCategory().getName();
        return new ServiceResponse(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getEstimatedDuration(),
                service.getCategory().getName()

                );

    }
    public String updateService(Long id, ServiceRequest request) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Service Not Found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category Not Found"));
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setEstimatedDuration(request.getEstimatedDuration());
        service.setCategory(category);
        service.setUpdatedAt(LocalDateTime.now());
        serviceRepository.save(service);
        return "Service Updated Successfully";
    }
    public String deleteService(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Service Not Found"));

        serviceRepository.delete(service);
        return "Service Deleted Successfully";
    }
    public List<ServiceResponse> getServicesByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category Not Found"));

        List<Service> services = serviceRepository.findByCategory(category);

        return services.stream()
                .map(service -> new ServiceResponse(
                        service.getId(),
                        service.getName(),
                        service.getDescription(),
                        service.getPrice(),
                        service.getEstimatedDuration(),
                        service.getCategory().getName()
                ))
                .toList();
    }


}