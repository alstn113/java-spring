package com.project.platform.domain.post.dto.request;

import com.project.platform.domain.post.domain.Post;

public record PostCreateRequest(String title, String content) {
    public Post toPost() {
        return new Post(title, content);
    }
}