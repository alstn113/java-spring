package com.project.platform.post.domain.repository;

import com.project.platform.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);

    boolean existsByMemberIdAndId(Long memberId, Long postId);
}
