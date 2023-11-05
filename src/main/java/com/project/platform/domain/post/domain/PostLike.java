package com.project.platform.domain.post.domain;

import com.project.platform.common.domain.BaseEntity;
import com.project.platform.domain.member.domain.Member;
import jakarta.persistence.Column;
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

    @Column(nullable = false, name = "post_id")
    private Long postId;

    @Column(nullable = false, name = "member_id")
    private Long memberId;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "post_id", insertable = false, updatable = false)
    private Post post;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "member_id", insertable = false, updatable = false)
    private Member member;

    protected PostLike() {
    }

    public PostLike(final Long id, final Long postId, final Long memberId, final Post post, final Member member) {
        this.id = id;
        this.postId = postId;
        this.memberId = memberId;
        this.post = post;
        this.member = member;
    }

    public PostLike(final Long postId, final Long memberId) {
        this(null, postId, memberId, null, null);
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Post getPost() {
        return post;
    }

    public Member getMember() {
        return member;
    }
}
