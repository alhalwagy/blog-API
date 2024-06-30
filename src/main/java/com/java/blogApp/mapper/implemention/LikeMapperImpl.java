package com.java.blogApp.mapper.implemention;

import com.java.blogApp.dto.like.LikeResponseModel;
import com.java.blogApp.entity.Like;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;
import com.java.blogApp.mapper.LikeMapper;
import com.java.blogApp.mapper.PostMapper;
import com.java.blogApp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeMapperImpl implements LikeMapper {

  private final PostMapper postMapper;
  private final UserMapper userMapper;

  @Override
  public LikeResponseModel toResponse(Like like) {
    return LikeResponseModel.builder()
        .id(like.getId())
        .createdAt(like.getCreatedAt())
        .updatedAt(like.getUpdatedAt())
        .postResponseModel(postMapper.toResponse(like.getPost()))
        .userResponseModel(userMapper.toResponse(like.getUser()))
        .build();
  }
}
