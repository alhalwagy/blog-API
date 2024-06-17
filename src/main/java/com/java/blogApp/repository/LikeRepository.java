package com.java.blogApp.repository;

import com.java.blogApp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Integer> {

}
