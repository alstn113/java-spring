package com.project.platform.domain.comment.domain;

import com.project.platform.domain.post.domain.Post;
import com.project.platform.domain.member.domain.Member;
import jakarta.persistence.*;

import java.util.Objects;

import static java.util.Objects.hash;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;


    protected Comment() {
    }

    public Comment(final Long id, final String content, final Post post) {
        this.id = id;
        this.content = content;
        this.post = post;
    }

    public Comment(final String content, final Post post) {
        this(null, content, post);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Post getPost() {
        return post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(o.getClass())) {
            return false;
        }
        final Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }

}
