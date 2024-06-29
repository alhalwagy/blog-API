package com.java.blogApp.mapper.implemention;

import com.java.blogApp.dto.post.PostRequestModel;
import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;
import com.java.blogApp.mapper.PostMapper;
import com.java.blogApp.mapper.TagMapper;
import com.java.blogApp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PostMapperImpl implements PostMapper {

  private final TagMapper tagMapper;
  private final UserMapper userMapper;

  @Override
  public PostResponseModel toResponse(Post post) {
    return PostResponseModel.builder()
        .id(post.getId())
        .content(post.getContent())
        .summary(post.getSummary())
        .title(post.getTitle())
        .tags(post.getTags().stream().map(tagMapper::toResponse).toList())
        .Author(userMapper.toResponse(post.getUser()))
        .publishedAt(post.getPublishedAt())
        .updatedAt(post.getUpdatedAt())
        .build();
  }


  @Override
  public Post toEntity(PostRequestModel postRequestModel, User user) {
    return Post.builder()
        .content(postRequestModel.getContent())
        .summary(postRequestModel.getSummary())
        .publishedAt(new Timestamp(System.currentTimeMillis()))
        .updatedAt(new Timestamp(System.currentTimeMillis()))
        .title(postRequestModel.getTitle())
            .comments(new ArrayList<>())
            .categories(new ArrayList<>())
            .likes(new ArrayList<>())
            .user(user)
            .tags(new ArrayList<>())
        .build();
  }
}
