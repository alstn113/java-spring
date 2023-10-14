package com.project.platform.module.post.presentation.dto;

import com.project.platform.module.post.domain.Post;

public record PostCreateRequest(String title, String content) {
    public Post toPost() {
        return new Post(title, content);
    }
}