package com.project.platform.module.member.respository;

import com.project.platform.module.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Post, Long> {
}
