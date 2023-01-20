package com.ilyaskumin.k8scourse.userservice.service;

import com.ilyaskumin.k8scourse.userservice.model.User;
import com.ilyaskumin.k8scourse.userservice.model.dto.request.CreateUserRequest;
import com.ilyaskumin.k8scourse.userservice.model.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User convert(CreateUserRequest createuserRequest);
    UserResponse convert(User user);
}
