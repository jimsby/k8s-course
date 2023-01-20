package com.ilyaskumin.k8scourse.userservice.controller;

import com.ilyaskumin.k8scourse.userservice.model.dto.request.CreateUserRequest;
import com.ilyaskumin.k8scourse.userservice.model.dto.request.UpdateUserRequest;
import com.ilyaskumin.k8scourse.userservice.model.dto.response.UserResponse;
import com.ilyaskumin.k8scourse.userservice.repository.UserRepository;
import com.ilyaskumin.k8scourse.userservice.service.UserMapper;
import com.ilyaskumin.k8scourse.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController{

    private final UserMapper userMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<UserResponse> createUser(CreateUserRequest createPostRequest) {
        var user = userMapper.convert(createPostRequest);
        user.setAmountOfPosts(0L);
        var savedUser = userRepository.save(user);
        log.info("New user {} has created", savedUser.getId());
        return ResponseEntity.ok(userMapper.convert(savedUser));
    }

    @Override
    public ResponseEntity<UserResponse> getUser(Long id) {
        var user = userService.findUser(id);
        return ResponseEntity.ok(userMapper.convert(user));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(Long id) {
        var user = userService.findUser(id);
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long id, UpdateUserRequest updatePostRequest) {
        var updatedUser = userService.updateUser(id, updatePostRequest);
        return ResponseEntity.ok(userMapper.convert(updatedUser));
    }
}
