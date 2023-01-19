package com.ilyaskumin.k8scourse.postservice.controller;

import com.ilyaskumin.k8scourse.postservice.model.dto.request.CreatePostRequest;
import com.ilyaskumin.k8scourse.postservice.model.dto.request.UpdatePostRequest;
import com.ilyaskumin.k8scourse.postservice.model.dto.response.PostResponse;
import com.ilyaskumin.k8scourse.postservice.repository.PostRepository;
import com.ilyaskumin.k8scourse.postservice.service.PostMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class PostControllerImpl implements PostController {

    private final PostMapper mapper;
    private final PostRepository repository;

    @Override
    @Transactional
    public ResponseEntity<PostResponse> publishPost(CreatePostRequest createPostRequest) {
        var post = mapper.convert(createPostRequest);
        var savedPost = repository.save(post);
        return ResponseEntity.ok(mapper.convert(savedPost));
    }

    @Override
    public ResponseEntity<PostResponse> getPost(Long id) {
        var post = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post doesn’t exist with given id"));
        return ResponseEntity.ok(mapper.convert(post));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deletePost(Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> updatePost(Long id, UpdatePostRequest updatePostRequest) {
        repository.findById(id).ifPresent(p -> p.setText(updatePostRequest.text()));
        return ResponseEntity.ok().build();
    }
}
