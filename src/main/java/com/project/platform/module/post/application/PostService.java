package com.project.platform.module.post.application;

import com.project.platform.exception.BadRequestException;
import com.project.platform.exception.ErrorCode;
import com.project.platform.module.post.domain.Post;
import com.project.platform.module.post.domain.repository.PostRepository;
import com.project.platform.module.post.dto.PostCreateRequest;
import com.project.platform.module.post.dto.PostResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse createPost(PostCreateRequest postCreateRequest) {
        Post post = postCreateRequest.toPost();
        postRepository.save(post);
        return PostResponse.fromPost(post);
    }

    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::fromPost)
                .toList();
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.fromPost(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
