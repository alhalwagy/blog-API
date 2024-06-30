package com.java.blogApp.repository;

import com.java.blogApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    Optional<Category> findByName(String name);
}
