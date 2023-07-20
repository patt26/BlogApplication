package com.project.blogapplication.Repository;

import com.project.blogapplication.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
