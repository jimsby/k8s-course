package com.ilyaskumin.k8scourse.postservice.service;

import com.ilyaskumin.k8scourse.postservice.model.Post;
import com.ilyaskumin.k8scourse.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private static final String POST_NOT_EXIST_MESSAGE = "Post doesn’t exist with given id";

    private final PostRepository postRepository;

    private Long getPostCount(Long userId) {
        return postRepository.countAllByAuthorId(userId);
    }

    public void updatePostCount(Long userId) {
        var count = getPostCount(userId);
        log.info("Count of posts for user {} changed to: {}", userId, count);
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, POST_NOT_EXIST_MESSAGE));
    }
}
