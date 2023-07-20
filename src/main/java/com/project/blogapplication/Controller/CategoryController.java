package com.project.blogapplication.Controller;

import com.project.blogapplication.Payload.CategoryDto;
import com.project.blogapplication.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryDto categoryDto){
        CategoryDto categoryDto1=categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1,HttpStatus.CREATED);
    }
}
