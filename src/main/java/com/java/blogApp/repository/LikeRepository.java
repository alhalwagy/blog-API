package com.java.blogApp.repository;

import com.java.blogApp.entity.Like;
import com.java.blogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    boolean existsByUser(User user);
}
