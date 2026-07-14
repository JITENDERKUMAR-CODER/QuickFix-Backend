package com.quickfix.service;

import com.quickfix.dto.CategoryRequest;
import com.quickfix.entity.Category;
import com.quickfix.repository.CategoryRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepositorie categoryRepositorie;

    public String createCategory(CategoryRequest request) {

        if (categoryRepositorie.existsByName(request.getName())) {
            return "Category Already Exists";
        }

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setIcon(request.getIcon());

        category.setActive(true);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        categoryRepositorie.save(category);

        return "Category Created Successfully";
    }
}