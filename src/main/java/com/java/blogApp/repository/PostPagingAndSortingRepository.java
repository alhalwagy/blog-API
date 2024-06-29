package com.java.blogApp.repository;

import com.java.blogApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostPagingAndSortingRepository extends PagingAndSortingRepository<Post, Integer> {

    Post save(Post post);

    void deleteById(int postId);

    List<Post> findAll();
}
