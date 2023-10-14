package com.project.platform.module.post.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.platform.module.post.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
