package com.ilyaskumin.k8scourse.postservice.controller;

import com.ilyaskumin.k8scourse.postservice.model.dto.request.CreatePostRequest;
import com.ilyaskumin.k8scourse.postservice.model.dto.request.UpdatePostRequest;
import com.ilyaskumin.k8scourse.postservice.model.dto.response.PostResponse;
import com.ilyaskumin.k8scourse.postservice.repository.PostRepository;
import com.ilyaskumin.k8scourse.postservice.service.PostMapper;
import com.ilyaskumin.k8scourse.postservice.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostControllerImpl implements PostController {

    private final PostMapper mapper;
    private final PostRepository repository;

    private final PostService postService;

    @Override
    @Transactional
    public ResponseEntity<PostResponse> publishPost(CreatePostRequest createPostRequest) {
        var post = mapper.convert(createPostRequest);
        var savedPost = repository.save(post);
        log.info("New post {} has created by user: {}", savedPost.getId(), savedPost.getAuthorId());
        postService.updatePostCount(savedPost.getAuthorId());
        return ResponseEntity.ok(mapper.convert(savedPost));
    }

    @Override
    public ResponseEntity<PostResponse> getPost(Long id) {
        var post = postService.findPost(id);
        return ResponseEntity.ok(mapper.convert(post));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deletePost(Long id) {
        var authorId = postService.findPost(id).getAuthorId();
        repository.deleteById(id);
        log.info("Post {} has deleted", id);
        postService.updatePostCount(authorId);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<PostResponse> updatePost(Long id, UpdatePostRequest updatePostRequest) {
        var post = postService.findPost(id);
        post.setText(updatePostRequest.text());
        log.info("Text for post {} has updated", post.getId());
        return ResponseEntity.ok(mapper.convert(post));
    }
}
