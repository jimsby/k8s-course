package com.ilyaskumin.k8scourse.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@Table(name = "user_table")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private Long amountOfPosts;
    @CreatedDate
    private Instant createdAt;
}
