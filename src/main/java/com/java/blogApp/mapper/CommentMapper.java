package com.java.blogApp.mapper;

import com.java.blogApp.dto.comment.CommentRequestModel;
import com.java.blogApp.dto.comment.CommentResponseModel;
import com.java.blogApp.entity.Comment;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;

public interface CommentMapper {

  CommentResponseModel toResponse(Comment comment);

  Comment toEntity(CommentRequestModel commentRequestModel, User user, Post post);
}
