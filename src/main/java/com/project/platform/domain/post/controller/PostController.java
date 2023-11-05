package com.project.platform.domain.post.controller;

import com.project.platform.domain.auth.controller.resolver.Auth;
import com.project.platform.domain.auth.controller.resolver.MemberOnly;
import com.project.platform.domain.auth.domain.Accessor;
import com.project.platform.domain.post.dto.request.PostCreateRequest;
import com.project.platform.domain.post.dto.response.PostResponse;
import com.project.platform.domain.post.service.PostService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @MemberOnly
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest postCreateRequest,
                                                   @Auth Accessor accessor) {
        final PostResponse postResponse = postService.createPost(postCreateRequest, accessor);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        final List<PostResponse> postResponses = postService.getAllPosts();
        return ResponseEntity.ok(postResponses);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        final PostResponse postResponse = postService.getPostById(postId);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/{postId}")
    @MemberOnly
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @Auth Accessor accessor) {
        postService.deletePostById(postId, accessor);
        return ResponseEntity.ok().build();
    }
}
