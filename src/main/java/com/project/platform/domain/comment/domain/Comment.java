package com.project.platform.domain.comment.domain;

import static java.util.Objects.hash;

import com.project.platform.domain.member.domain.Member;
import com.project.platform.domain.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;


    protected Comment() {
    }

    public Comment(final Long id, final String text, final Post post) {
        this.id = id;
        this.text = text;
        this.post = post;
    }

    public Comment(final String text, final Post post) {
        this(null, text, post);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return text;
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
