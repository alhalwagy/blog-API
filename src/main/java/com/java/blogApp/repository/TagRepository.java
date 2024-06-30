package com.java.blogApp.repository;

import com.java.blogApp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    boolean existsByName(String name);

    Optional<Tag> findByName(String tagName);
}
