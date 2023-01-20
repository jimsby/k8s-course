package com.ilyaskumin.k8scourse.userservice.controller;

import com.ilyaskumin.k8scourse.userservice.model.dto.request.CreateUserRequest;
import com.ilyaskumin.k8scourse.userservice.model.dto.request.UpdateUserRequest;
import com.ilyaskumin.k8scourse.userservice.model.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public interface UserController {
    @PostMapping
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createPostRequest);

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
                                            @RequestBody UpdateUserRequest updatePostRequest);
}
