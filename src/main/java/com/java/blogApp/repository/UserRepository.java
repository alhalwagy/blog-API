package com.java.blogApp.repository;

import com.java.blogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String username);

  boolean existsByEmail(String email);

    List<User> findAllByEmail(String email);

  List<User> findAllByFirstname(String firstname);

  List<User> findAllByLastname(String lastname);
}
