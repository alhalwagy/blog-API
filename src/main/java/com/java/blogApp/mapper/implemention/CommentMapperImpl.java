package com.java.blogApp.mapper.implemention;

import com.java.blogApp.dto.comment.CommentRequestModel;
import com.java.blogApp.dto.comment.CommentResponseModel;
import com.java.blogApp.entity.Comment;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;
import com.java.blogApp.mapper.CommentMapper;
import com.java.blogApp.mapper.PostMapper;
import com.java.blogApp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CommentMapperImpl implements CommentMapper {

  private final UserMapper userMapper;

  private final PostMapper postMapper;

  @Override
  public CommentResponseModel toResponse(Comment comment) {
    return CommentResponseModel.builder()
        .id(comment.getId())
        .content(comment.getContent())
        .author(userMapper.toResponse(comment.getUser()))
        .createdAt(comment.getCreatedAt())
        .updatedAt(comment.getUpdatedAt())
        .post(postMapper.toResponse(comment.getPost()))
        .build();
  }

  @Override
  public Comment toEntity(CommentRequestModel commentRequestModel, User user, Post post) {
    return Comment.builder()
        .content(commentRequestModel.getContent())
        .createdAt(new Timestamp(System.currentTimeMillis()))
        .updatedAt(new Timestamp(System.currentTimeMillis()))
        .user(user)
        .post(post)
        .build();
  }
}
