package com.java.blogApp.repository;

import com.java.blogApp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Integer> {

}
