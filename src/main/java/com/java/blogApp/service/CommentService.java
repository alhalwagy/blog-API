package com.java.blogApp.service;

import com.java.blogApp.dto.comment.CommentRequestModel;
import com.java.blogApp.dto.comment.CommentResponseModel;

import java.util.List;

public interface CommentService {

  List<CommentResponseModel> findAllComments();

  CommentResponseModel createComment(CommentRequestModel requestModel);

  CommentResponseModel updateComment(int id, CommentRequestModel commentRequestModel);

  CommentResponseModel findCommentById(int id);

  List<CommentResponseModel> findCommentsByPostId(int postId);

  void deleteCommentById(int id);
}
