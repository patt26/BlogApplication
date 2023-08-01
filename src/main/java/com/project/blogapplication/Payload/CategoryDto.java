package com.project.blogapplication.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long id;

    @NotEmpty
    @Size(min = 3,message = "Name should be greater then 3 characters")
    private String name;

    @NotEmpty
    @Size(min = 5,message = "Description should be greater than 3 characters")
    private String description;

}
