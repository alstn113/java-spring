package com.project.platform.domain.post.service;

import com.project.platform.domain.auth.domain.Accessor;
import com.project.platform.domain.member.domain.Member;
import com.project.platform.domain.member.service.MemberService;
import com.project.platform.domain.post.domain.Post;
import com.project.platform.domain.post.domain.PostLike;
import com.project.platform.domain.post.domain.repository.PostLikeRepository;
import com.project.platform.domain.post.domain.repository.PostRepository;
import com.project.platform.exception.ErrorCode;
import com.project.platform.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public PostLikeService(final MemberService memberService, final PostRepository postRepository,
                           final PostLikeRepository postLikeRepository) {
        this.memberService = memberService;
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public void likePost(Long postId, Accessor accessor) {
        final Member member = memberService.getMemberByIdOrElseThrow(accessor.getMemberId());
        final Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorCode.POST_NOT_FOUND)
        );
        final boolean isAlreadyLiked = postLikeRepository.existsByPostIdAndMemberId(post.getId(),
                member.getId());
        if (!isAlreadyLiked) {
            postLikeRepository.save(new PostLike(post.getId(), member.getId()));
        }
    }

    public void unlikePost(Long postId, Accessor accessor) {
        final Member member = memberService.getMemberByIdOrElseThrow(accessor.getMemberId());
        final Post post = postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(ErrorCode.POST_NOT_FOUND)
        );
        final boolean isAlreadyLiked = postLikeRepository.existsByPostIdAndMemberId(post.getId(),
                member.getId());
        if (isAlreadyLiked) {
            postLikeRepository.deleteByPostIdAndMemberId(post.getId(), member.getId());
        }
    }
}
