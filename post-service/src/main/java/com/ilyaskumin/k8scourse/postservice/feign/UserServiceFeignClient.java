package com.ilyaskumin.k8scourse.postservice.feign;

import com.ilyaskumin.k8scourse.postservice.model.dto.request.UpdateAmountOfPostsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @PutMapping(value = "/users/{id}", consumes = "application/json")
    void updateAmountOfPosts(@PathVariable("id") Long userId, UpdateAmountOfPostsRequest request);
}
