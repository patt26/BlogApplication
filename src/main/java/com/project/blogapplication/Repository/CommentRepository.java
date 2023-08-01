package com.project.blogapplication.Repository;

import com.project.blogapplication.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long postId);

    List<Comment> findCommentsByBodyContaining(String body);

}
