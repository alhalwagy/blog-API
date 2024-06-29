package com.java.blogApp.mapper.implemention;

import com.java.blogApp.entity.User;
import com.java.blogApp.mapper.UserMapper;
import com.java.blogApp.dto.user.UserResponseModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public UserResponseModel toResponse(User user) {
    return UserResponseModel.builder()
        .id(user.getId())
        .email(user.getEmail())
        .lastname(user.getLastname())
        .firstname(user.getFirstname())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .build();
  }
}
