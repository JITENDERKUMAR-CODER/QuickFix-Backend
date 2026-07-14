package com.quickfix.controller;

import com.quickfix.dto.CategoryRequest;
import com.quickfix.dto.CategoryResponse;
import com.quickfix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public String createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }
    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();

    }
    @GetMapping("/{id}")
    public CategoryResponse getCategoryByid(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    @PutMapping("/{id}")
    public String updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }


}
