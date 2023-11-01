package com.project.platform.domain.post.domain;

import com.project.platform.common.domain.BaseEntity;
import com.project.platform.domain.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PostLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Post post;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Member member;

    protected PostLike() {
    }

    public PostLike(final Long id, final Post post, final Member member) {
        this.id = id;
        this.post = post;
        this.member = member;
    }

    public PostLike(final Post post, final Member member) {
        this(null, post, member);
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public Member getMember() {
        return member;
    }
}
