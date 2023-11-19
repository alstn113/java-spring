package com.project.platform.domain.comment.controller;

import com.project.platform.domain.auth.controller.resolver.Auth;
import com.project.platform.domain.auth.controller.resolver.MemberOnly;
import com.project.platform.domain.auth.domain.Accessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    @GetMapping
    public void getComments() {
    }

    @PostMapping
    @MemberOnly
    public ResponseEntity<Void> createComment(@PathVariable Long postId,
                                              @Auth Accessor accessor) {
        
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment() {
    }
}
