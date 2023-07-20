package com.project.blogapplication.Service;

import com.project.blogapplication.Payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long id,CommentDto commentDto);

    List<CommentDto> findAllComments(Long id);

    CommentDto findById(Long postId,Long CommentId);

    CommentDto updateComment(Long postId,Long CommentId,CommentDto commentDto);

    void DeleteComment(Long postId,Long commentId);
}
