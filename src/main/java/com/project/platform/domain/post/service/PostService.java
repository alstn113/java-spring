package com.project.platform.domain.post.service;

import com.project.platform.domain.post.domain.Post;
import com.project.platform.domain.post.domain.repository.PostRepository;
import com.project.platform.domain.post.dto.request.PostCreateRequest;
import com.project.platform.domain.post.dto.response.PostResponse;
import com.project.platform.exception.BadRequestException;
import com.project.platform.exception.ErrorCode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse createPost(PostCreateRequest postCreateRequest) {
        final Post post = postCreateRequest.toPost();
        postRepository.save(post);
        return PostResponse.fromPost(post);
    }

    public List<PostResponse> getAllPosts() {
        final List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::fromPost)
                .toList();
    }

    public PostResponse getPostById(Long id) {
        final Post post = postRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.fromPost(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
