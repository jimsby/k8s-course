package com.ilyaskumin.k8scourse.userservice.model.dto.request;

public record UpdateUserRequest(String username, Long amountOfPosts) {
}
