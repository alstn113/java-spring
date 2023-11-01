package com.project.platform.domain.post.dto.response;

import com.project.platform.domain.comment.domain.Comment;
import com.project.platform.domain.member.domain.Member;
import com.project.platform.domain.post.domain.Post;
import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(Long id, String title, String content, Member member, List<Comment> comments,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {

    public static PostResponse fromPost(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getMember(), post.getComments(),
                post.getCreatedAt(), post.getUpdatedAt());
    }
}
