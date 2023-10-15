package com.project.platform.module.member.domain;


import com.project.platform.global.common.domain.BaseEntity;
import com.project.platform.module.comment.domain.Comment;
import com.project.platform.module.post.domain.Post;
import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = {MERGE, PERSIST})
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = {MERGE, PERSIST})
    private List<Comment> comments = new ArrayList<>();

    protected Member() {
    }

    public Member(String email, String password) {
        this(null, email, password);
    }

    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Member hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }
}
