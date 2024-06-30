package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.comment.CommentRequestModel;
import com.java.blogApp.dto.comment.CommentResponseModel;
import com.java.blogApp.entity.Comment;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.CommentMapper;
import com.java.blogApp.repository.CommentRepository;
import com.java.blogApp.repository.PostPagingAndSortingRepository;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final UserRepository userRepository;
  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;
  private final PostPagingAndSortingRepository postPagingAndSortingRepository;

  @Override
  public List<CommentResponseModel> findAllComments() {
    List<Comment> comments = commentRepository.findAll();
    return comments.stream().map(commentMapper::toResponse).toList();
  }

  @Override
  public CommentResponseModel createComment(CommentRequestModel requestModel) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    new NotAuthToSeeResourseException(
                        "you are not authorized to create this comment. Please register with the email."));

    Post post =
        postPagingAndSortingRepository
            .findById(requestModel.getPostId())
            .orElseThrow(() -> new RecordNotFoundException("Post not found to make comments. "));

    Comment comment = commentRepository.save(commentMapper.toEntity(requestModel, user, post));

    return commentMapper.toResponse(comment);
  }

  @Override
  public CommentResponseModel updateComment(int id, CommentRequestModel commentRequestModel) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    new NotAuthToSeeResourseException(
                        "you are not authorized to update this comment. Please register with the email."));

    Comment comment =
        commentRepository
            .findById(id)
            .orElseThrow(
                () -> new RecordNotFoundException("Comment not found to update comment. "));

    comment.setContent(commentRequestModel.getContent());
    return commentMapper.toResponse(commentRepository.save(comment));
  }

  @Override
  public CommentResponseModel findCommentById(int id) {

    return commentMapper.toResponse(
        commentRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Comment not found to find comment. ")));
  }

  @Override
  public List<CommentResponseModel> findCommentsByPostId(int postId) {

    Post post =
        postPagingAndSortingRepository
            .findById(postId)
            .orElseThrow(() -> new RecordNotFoundException("Post not found to find comments. "));

    List<Comment> comments = post.getComments();

    return comments.stream().map(commentMapper::toResponse).toList();
  }

  @Override
  public void deleteCommentById(int id) {

    Comment comment =
        commentRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException("Comment not found to find comment. "));

    commentRepository.delete(comment);
  }
}
