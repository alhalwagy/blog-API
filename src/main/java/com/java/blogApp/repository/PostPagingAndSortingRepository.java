package com.java.blogApp.repository;

import com.java.blogApp.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostPagingAndSortingRepository extends PagingAndSortingRepository<Post, Integer> {

    Post save(Post post);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM posts WHERE id = ?1", nativeQuery = true)
    void deleteById(int postId);

    List<Post> findAll();
}
