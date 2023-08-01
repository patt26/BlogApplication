package com.project.blogapplication.Service;

import com.project.blogapplication.Payload.CategoryDto;
import com.project.blogapplication.Payload.CommentDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    void deleteCategory(Long id);


}
