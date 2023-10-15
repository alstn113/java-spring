package com.project.platform.module.post.presentation;

import com.project.platform.module.post.application.PostService;
import com.project.platform.module.post.domain.Post;
import com.project.platform.module.post.presentation.dto.PostCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        Post post = postService.createPost(postCreateRequest);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            // 이게 아닌듯...
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
