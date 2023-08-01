package com.project.blogapplication.Controller;

import com.project.blogapplication.Payload.CategoryDto;
import com.project.blogapplication.Payload.PostDto;
import com.project.blogapplication.Service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD REST APIs for Category section in Blog Application"
)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryDto categoryDto){
        CategoryDto categoryDto1=categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        CategoryDto categoryDto=categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDto,HttpStatus.FOUND);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.FOUND);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long id){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto,id),HttpStatus.OK);
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return  ResponseEntity.ok("Category deleted Successfully!!");
    }


}
