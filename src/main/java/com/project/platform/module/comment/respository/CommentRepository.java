package com.project.platform.module.comment.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.platform.module.comment.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
