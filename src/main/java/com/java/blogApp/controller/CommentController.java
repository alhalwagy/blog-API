package com.java.blogApp.controller;

import com.java.blogApp.dto.comment.CommentRequestModel;
import com.java.blogApp.dto.comment.CommentResponseModel;
import com.java.blogApp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping("")
  public List<CommentResponseModel> getAllComments() {
    return commentService.findAllComments();
  }

  @PostMapping("")
  public CommentResponseModel createComment(@RequestBody CommentRequestModel requestModel) {

    return commentService.createComment(requestModel);
  }

  @PutMapping("{id}")
  public CommentResponseModel updateComment(
      @PathVariable int id, @RequestBody CommentRequestModel commentRequestModel) {

    return commentService.updateComment(id, commentRequestModel);
  }

  @GetMapping("{id}")
  public CommentResponseModel getCommentById(@PathVariable int id) {
    return commentService.findCommentById(id);
  }

  @GetMapping("/post-comments/{postId}")
  public List<CommentResponseModel> getCommentsByPostId(@PathVariable int postId) {
    return commentService.findCommentsByPostId(postId);
  }

  @DeleteMapping("{id}")
  public void deleteComment(@PathVariable int id) {
      commentService.deleteCommentById(id);
  }

}
