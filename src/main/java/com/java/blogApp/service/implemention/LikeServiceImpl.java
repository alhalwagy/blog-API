package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.like.LikeResponseModel;
import com.java.blogApp.entity.Like;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.LikeMapper;
import com.java.blogApp.repository.LikeRepository;
import com.java.blogApp.repository.PostPagingAndSortingRepository;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

  private final LikeRepository likeRepository;
  private final LikeMapper likeMapper;
  private final UserRepository userRepository;
  private final PostPagingAndSortingRepository postPagingAndSortingRepository;

  @Override
  public LikeResponseModel findById(int id) {
    return likeMapper.toResponse(
        likeRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException("like not found")));
  }

  @Override
  public LikeResponseModel createLike(int postId) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RecordNotFoundException("User Email " + email + " Not Found!"));

    if (likeRepository.existsByUser(user)) {
      throw new RecordNotFoundException("You Already created like");
    }

    Post post =
        postPagingAndSortingRepository
            .findById(postId)
            .orElseThrow(
                () -> new RecordNotFoundException("That Post Id " + postId + " Is Not Found!"));

    Like like =
        Like.builder()
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .updatedAt(new Timestamp(System.currentTimeMillis()))
            .user(user)
            .post(post)
            .build();

    return likeMapper.toResponse(likeRepository.save(like));
  }

  @Override
  public void deleteLike(int likeId) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RecordNotFoundException("User Email " + email + " Not Found!"));

    Like like =
        likeRepository
            .findById(likeId)
            .orElseThrow(
                () -> new RecordNotFoundException("That Like Id " + likeId + " Is Not Found!"));

    if (like.getUser().getId() != user.getId())
      throw new NotAuthToSeeResourseException("You are Not Auth to Do That");

    likeRepository.delete(like);
  }

  @Override
  public List<LikeResponseModel> getLikesByPostId(int postId) {
    Post post =
        postPagingAndSortingRepository
            .findById(postId)
            .orElseThrow(() -> new RecordNotFoundException("Post Not Found"));
    return post.getLikes().stream().map(likeMapper::toResponse).toList();
  }
}
