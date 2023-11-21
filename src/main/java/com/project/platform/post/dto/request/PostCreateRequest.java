package com.project.platform.post.dto.request;

import com.project.platform.member.domain.Member;
import com.project.platform.post.domain.Post;

public record PostCreateRequest(String title, String content) {
    public Post toPost(Member member) {
        return new Post(title, content, member);
    }
}