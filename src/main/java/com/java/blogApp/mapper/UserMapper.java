package com.java.blogApp.mapper;

import com.java.blogApp.entity.User;
import com.java.blogApp.dto.user.UserResponseModel;

public interface UserMapper {

  UserResponseModel toResponse(User user);
}
