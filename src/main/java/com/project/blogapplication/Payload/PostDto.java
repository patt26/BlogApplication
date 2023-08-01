package com.project.blogapplication.Payload;

import io.swagger.v3.oas.annotations.media.Schema;
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

@Schema(
        description = "Post DTO model information"
)
public class PostDto {
    private Long id;

    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty(message = "Title should not be empty!!")
    @Size(min = 3, message = "Title should have at least 3 characters")
    private String title;

    @Schema(description = "Blog Post Description")

    @NotEmpty(message = "Description should not be empty!!")
    @Size(min = 10, message = "Description should have 10 at least characters")
    private String description;

    @Schema(
            description = "Blog Post content type"
    )

    @NotEmpty(message = "Content should not be empty")
    private String content;
    private List<CommentDto> comments;

    @Schema(
            description = "Comments Id"
    )
    private Long categoryId;
}
