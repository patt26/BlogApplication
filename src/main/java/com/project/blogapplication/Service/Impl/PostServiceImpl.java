package com.project.blogapplication.Service.Impl;

import com.project.blogapplication.Exceptions.ResourceNotFoundException;
import com.project.blogapplication.Model.Post;
import com.project.blogapplication.Payload.PostDto;
import com.project.blogapplication.Payload.PostResponse;
import com.project.blogapplication.Repository.PostRepository;
import com.project.blogapplication.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert dto to entity

        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        //convert entity to DTO

        return mapToDTO(newPost);

    }

    //convert entity to DTO
    private PostDto mapToDTO(Post newPost) {
        return modelMapper.map(newPost,PostDto.class);
    }

    //convert dto to entity
    private Post mapToEntity(PostDto postDto) {
       return modelMapper.map(postDto,Post.class);
    }

    @Override
    public PostResponse findAllPosts(int pageNo, int pageSize, String sort, String order) {

        Sort sortList;
        if (order.equalsIgnoreCase("desc")) {
            sortList = Sort.by(Sort.Order.desc(sort));
        } else {
            sortList = Sort.by(Sort.Order.asc(sort));
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortList);
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPost = posts.getContent();

        List<PostDto> content = listOfPost.stream().map(this::mapToDTO).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(postResponse.isLast());
        return postResponse;
    }


    @Override
    public PostDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto update(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePosts(Long id) {
        Post deletePost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(deletePost);

    }
}

