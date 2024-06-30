package com.java.blogApp.mapper;

import com.java.blogApp.dto.like.LikeResponseModel;
import com.java.blogApp.entity.Like;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;

public interface LikeMapper {

  LikeResponseModel toResponse(Like like);
}
