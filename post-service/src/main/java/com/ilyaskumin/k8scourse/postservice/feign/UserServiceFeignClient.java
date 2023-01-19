package com.ilyaskumin.k8scourse.postservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {
}
