package com.project.platform.domain.post.domain;

import com.project.platform.common.domain.BaseEntity;
import com.project.platform.domain.comment.domain.Comment;
import com.project.platform.domain.member.domain.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostLike> postLikes;

    protected Post() {
    }

    public Post(final Long id, final String title, final String content, final Member member,
                final List<Comment> comments, final List<PostLike> postLikes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
        this.comments = comments;
        this.postLikes = postLikes;
    }

    public Post(final String title, final String content) {
        this(null, title, content, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Member getMember() {
        return member;
    }

    public List<PostLike> getPostLikes() {
        return postLikes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
