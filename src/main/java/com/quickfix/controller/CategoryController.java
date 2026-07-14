package com.quickfix.controller;

import com.quickfix.dto.CategoryRequest;
import com.quickfix.dto.CategoryResponse;
import com.quickfix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }


}
