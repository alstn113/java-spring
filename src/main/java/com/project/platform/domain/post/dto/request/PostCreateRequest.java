package com.project.platform.domain.post.dto.request;

import com.project.platform.domain.member.domain.Member;
import com.project.platform.domain.post.domain.Post;

public record PostCreateRequest(String title, String content) {
    public Post toPost(Member member) {
        return new Post(title, content, member);
    }
}