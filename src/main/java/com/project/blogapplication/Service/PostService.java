package com.project.blogapplication.Service;

import com.project.blogapplication.Payload.PostDto;
import com.project.blogapplication.Payload.PostResponse;

import java.util.List;


public interface PostService {
   PostDto createPost(PostDto postDto);

   PostResponse findAllPosts(int pageNo, int pageSize,String sort,String order);

   PostDto findById(Long id);

   PostDto update(PostDto postDto,Long id);

   void deletePosts(Long id);

   List<PostDto> getPostByCategory(Long categoryId);


}
