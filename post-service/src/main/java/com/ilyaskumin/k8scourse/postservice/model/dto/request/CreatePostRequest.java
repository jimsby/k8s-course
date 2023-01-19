package com.ilyaskumin.k8scourse.postservice.model.dto.request;

public record CreatePostRequest(Long authorId, String text) {
}
