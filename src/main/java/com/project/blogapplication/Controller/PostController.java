package com.project.blogapplication.Controller;

import com.project.blogapplication.Payload.PostDto;
import com.project.blogapplication.Payload.PostResponse;
import com.project.blogapplication.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPosts(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole({'USER','ADMIN'})")
    @GetMapping("")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue ="0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sort,
            @RequestParam(value="order",defaultValue = "asc",required = false)String order)
            {
        return new ResponseEntity<>( postService.findAllPosts(pageNo,pageSize,sort,order),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<PostDto> findByIdPost(@PathVariable Long id){
        return new ResponseEntity<>(postService.findById(id),HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePosts(@Valid @RequestBody PostDto postDto,@PathVariable Long id){
        return new ResponseEntity<>(postService.update(postDto,id),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        postService.deletePosts(id);
        return new ResponseEntity<>("Successfully deleted the post",HttpStatus.OK);
    }

}
