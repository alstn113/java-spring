package com.project.platform.module.post.dto.request;

import com.project.platform.module.post.domain.Post;

public record PostCreateRequest(String title, String content) {
    public Post toPost() {
        return new Post(title, content);
    }
}