package com.quickfix.service;

import com.quickfix.dto.CategoryRequest;
import com.quickfix.dto.CategoryResponse;
import com.quickfix.entity.Category;
import com.quickfix.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepositorie;

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
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepositorie.findAll();
        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        category.getDescription(),
                        category.getIcon()
                ))
                .toList();

    }
    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepositorie.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category Not Found"));

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getIcon()
        );
    }
    public String updateCategory(Long id, CategoryRequest request) {
    Category category = categoryRepositorie.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Category Not Found"));
    if(!category.getName().equals(request.getName())
            && categoryRepositorie.existsByName(request.getName())){
        return "Category Already Exists";
    }
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setIcon(request.getIcon());
        category.setUpdatedAt(LocalDateTime.now());

        categoryRepositorie.save(category);

        return "Category Updated Successfully";

}
public String deleteCategory(Long id) {
    Category category = categoryRepositorie.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Category Not Found"));

    categoryRepositorie.delete(category);
    return "Category Deleted Successfully";
}
}