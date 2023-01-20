package com.ilyaskumin.k8scourse.postservice.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePostRequest(@NotBlank String text) {
}
