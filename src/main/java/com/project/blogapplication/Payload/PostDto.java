package com.project.blogapplication.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long id;

    @NotEmpty(message = "Title should not be empty!!")
    @Size(min = 3,message = "Title should have at least 3 characters")
    private String title;

    @NotEmpty(message = "Description should not be empty!!")
    @Size(min = 10,message = "Description should have 10 at least characters")
    private String description;

    @NotEmpty(message = "Content should not be empty")
    private String content;
    private List<CommentDto> comments;
}
