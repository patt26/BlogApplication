package com.project.blogapplication.Payload;

import jakarta.validation.constraints.Email;
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
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min=3,message = "Name should be at least 3 characters")
    private String name;

    @NotEmpty(message = "Email is mandatory should not be empty")
    @Email(message ="Email should be in proper format" )
    private String email;

    @NotEmpty(message = "Body must not be empty")
    @Size(min = 10,message = "Comment body must have at least 10 characters")
    private String body;
}
