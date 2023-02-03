package com.ilyaskumin.k8scourse.postservice.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreatePostRequest(@Min(1) Long authorId, @NotBlank String text, String topic) {
}
