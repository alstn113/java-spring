package com.project.platform.module.post.dto;

import com.project.platform.module.comment.domain.Comment;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.post.domain.Post;
import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(Long id, String title, String content, Member member, List<Comment> comments,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static PostResponse fromPost(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getMember(), post.getComments(),
                post.getCreatedAt(), post.getUpdatedAt());
    }
}
