package com.ilyaskumin.k8scourse.postservice.service;

import com.ilyaskumin.k8scourse.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long getPostCount(Long userId) {
        return postRepository.countAllByAuthorId(userId);
    }
}
