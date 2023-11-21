package com.project.platform.post.controller;

import com.project.platform.auth.controller.resolver.Auth;
import com.project.platform.auth.controller.resolver.MemberOnly;
import com.project.platform.auth.domain.Accessor;
import com.project.platform.post.service.PostLikeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/{postId}/likes")
public class PostLikeController {
    private final PostLikeService postLikeService;

    public PostLikeController(final PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping
    @MemberOnly
    public void likePost(
            @PathVariable Long postId,
            @Auth Accessor accessor
    ) {
        this.postLikeService.likePost(postId, accessor);
    }

    @DeleteMapping
    @MemberOnly
    public void unlikePost(
            @PathVariable Long postId,
            @Auth Accessor accessor
    ) {
        this.postLikeService.unlikePost(postId, accessor);
    }
}
