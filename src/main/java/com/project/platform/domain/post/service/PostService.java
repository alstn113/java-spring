package com.project.platform.domain.post.service;

import com.project.platform.domain.auth.domain.Accessor;
import com.project.platform.domain.member.domain.Member;
import com.project.platform.domain.member.service.MemberService;
import com.project.platform.domain.post.domain.Post;
import com.project.platform.domain.post.domain.repository.PostRepository;
import com.project.platform.domain.post.dto.request.PostCreateRequest;
import com.project.platform.domain.post.dto.response.PostResponse;
import com.project.platform.exception.BadRequestException;
import com.project.platform.exception.ErrorCode;
import com.project.platform.exception.NotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;

    public PostService(final PostRepository postRepository, final MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    public PostResponse createPost(PostCreateRequest postCreateRequest, Accessor accessor) {
        final Member member = memberService.getMemberByIdOrElseThrow(accessor.getMemberId());
        validateIsPostTitleDuplicate(postCreateRequest.title());
        final Post post = postCreateRequest.toPost(member);
        postRepository.save(post);
        return PostResponse.fromPost(post);
    }

    public List<PostResponse> getAllPosts() {
        final List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::fromPost)
                .toList();
    }

    public PostResponse getPostById(Long postId) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));
        return PostResponse.fromPost(post);
    }

    public void deletePostById(Long postId, Accessor accessor) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));
        validateIsAuthor(accessor.getMemberId(), post.getId());
        postRepository.deleteById(postId);
    }

    private void validateIsPostTitleDuplicate(String title) {
        final boolean isTitleExists = postRepository.existsByTitle(title);
        if (isTitleExists) {
            throw new BadRequestException(ErrorCode.POST_TITLE_EXISTS);
        }
    }

    private void validateIsAuthor(Long memberId, Long postId) {
        final boolean isAuthor = postRepository.existsByMemberIdAndId(memberId, postId);
        if (!isAuthor) {
            throw new BadRequestException(ErrorCode.POST_NOT_AUTHOR);
        }
    }
}
