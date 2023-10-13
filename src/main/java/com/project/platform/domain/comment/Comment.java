package com.project.platform.domain.comment;

import com.project.platform.domain.post.Post;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.hash;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    private Post post;

    protected Comment() {
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Post getPost() {
        return post;
    }

    public Comment(
            final Long id,
            final String content,
            final LocalDateTime createdAt,
            final Post post
    ) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.post = post;
    }

    public Comment(
            final String content,
            final LocalDateTime createdAt,
            final Post post
    ) {
        this(null, content, createdAt, post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !getClass().equals(o.getClass())) return false;
        final Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

}
