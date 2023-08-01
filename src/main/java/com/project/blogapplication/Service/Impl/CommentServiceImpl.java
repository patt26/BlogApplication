package com.project.blogapplication.Service.Impl;

import com.project.blogapplication.Exceptions.BlogApiException;
import com.project.blogapplication.Exceptions.ResourceNotFoundException;
import com.project.blogapplication.Model.Comment;
import com.project.blogapplication.Model.Post;
import com.project.blogapplication.Payload.CommentDto;
import com.project.blogapplication.Repository.CommentRepository;
import com.project.blogapplication.Repository.PostRepository;
import com.project.blogapplication.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    private CommentDto mapToDto(Comment comment) {
       return modelMapper.map(comment,CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto,Comment.class);
    }

    @Override
    public CommentDto createComment(Long id, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        comment.setPost(post);
        Comment entity = commentRepository.save(comment);
        return mapToDto(entity);
    }

    @Override
    public List<CommentDto> findAllComments(Long id) {
        List<Comment> comment = commentRepository.findByPostId(id);
        return comment.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public CommentDto findById(Long postId, Long CommentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        Comment comment = commentRepository.findById(CommentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", CommentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Comments does not belong to any post");
        }
        return mapToDto(comment);


    }

    @Override
    public CommentDto updateComment(Long postId, Long CommentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
        Comment comment = commentRepository.findById(CommentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", CommentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Comments does not belong to any post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        commentRepository.save(comment);
        return mapToDto(comment);

    }

    @Override
    public void DeleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Comments does not belong to any post");
        }
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> searchComments(String body, Long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        List<Comment> comment = commentRepository.findCommentsByBodyContaining(body);

        if(comment.isEmpty()&&post.getId()==null){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Given body is not found");
        }

        return comment.stream().map(this::mapToDto).collect(Collectors.toList());
    }




}
