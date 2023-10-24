package com.project.platform.module.post.service;

import com.project.platform.module.post.domain.Post;
import com.project.platform.module.post.dto.PostCreateRequest;
import com.project.platform.module.post.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostCreateRequest postCreateRequest) {
        Post post = postCreateRequest.toPost();
        postRepository.save(post);
        return post;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
