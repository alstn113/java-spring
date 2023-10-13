package com.project.platform.controller.dto;

import com.project.platform.domain.post.Post;

public record PostCreateRequest(String title, String content) {
    public Post toPost() {
        return new Post(title, content);
    }
}