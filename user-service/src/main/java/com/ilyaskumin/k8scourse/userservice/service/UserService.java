package com.ilyaskumin.k8scourse.userservice.service;

import com.ilyaskumin.k8scourse.userservice.model.User;
import com.ilyaskumin.k8scourse.userservice.model.dto.request.UpdateUserRequest;
import com.ilyaskumin.k8scourse.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_EXIST_MESSAGE = "User doesn’t exist with given id";
    private static final String VALIDATION_EXCEPTION_MESSAGE = "Validation error or request body is an invalid";
    private final UserRepository userRepository;

    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_EXIST_MESSAGE));
    }

    @Transactional
    public User updateUser(Long id, UpdateUserRequest updatePostRequest) {
        var user = findUser(id);

        var name = Optional.ofNullable(updatePostRequest)
                .map(UpdateUserRequest::username)
                .filter(StringUtils::hasText);

        var count = Optional.ofNullable(updatePostRequest)
                .map(UpdateUserRequest::amountOfPosts)
                .filter(countValue -> countValue >= 0);
        if(name.isEmpty() && count.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, VALIDATION_EXCEPTION_MESSAGE);
        }

        name.ifPresent(newName -> {
            user.setUsername(newName);
            log.info("Username for user {} has changed", user.getId());
        });
        count.ifPresent(countValue -> {
            user.setAmountOfPosts(countValue);
            log.info("Amount Of Posts for user {} has changed to {}", user.getId(), countValue);
        });

        userRepository.save(user);

        return user;
    }
}
