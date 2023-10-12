package com.example.demo.controller.dto;

import com.example.demo.domain.post.Post;

public record PostCreateRequest(String title, String content) {
    public Post toPost() {
        return new Post(title, content);
    }
}