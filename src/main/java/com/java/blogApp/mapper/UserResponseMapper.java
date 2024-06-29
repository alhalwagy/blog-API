package com.java.blogApp.mapper;

import com.java.blogApp.entity.User;
import com.java.blogApp.dto.user.UserResponseModel;

public interface UserResponseMapper {

  UserResponseModel toResponse(User user);
}
