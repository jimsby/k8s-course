package com.ilyaskumin.k8scourse.userservice.repository;

import com.ilyaskumin.k8scourse.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
