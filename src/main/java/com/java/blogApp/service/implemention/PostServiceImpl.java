package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.post.PostRequestModel;
import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.PostMapper;
import com.java.blogApp.repository.PostPagingAndSortingRepository;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostPagingAndSortingRepository postRepository;
  private final PostMapper postMapper;
  private final UserRepository userRepository;

  @Override
  public List<PostResponseModel> findAll() {
    List<Post> posts = postRepository.findAll();

    return posts.stream().map(postMapper::toResponse).toList();
  }

  @Override
  public PostResponseModel createPost(PostRequestModel postRequestModel) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RecordNotFoundException("User Not Found"));

    Post post = postRepository.save(postMapper.toEntity(postRequestModel, user));

    return postMapper.toResponse(post);
  }

  @Override
  public List<PostResponseModel> getMyPosts() {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RecordNotFoundException("User Not Found"));

    List<Post> posts = user.getPosts();

    return posts.stream().map(postMapper::toResponse).toList();
  }

  @Override
  public PostResponseModel updatePost(int id, PostRequestModel postRequestModel) {

    Post getPost = checkIfUserAuthority(id);

    getPost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    getPost.setTitle(postRequestModel.getTitle());
    getPost.setContent(postRequestModel.getContent());
    getPost.setSummary(postRequestModel.getSummary());

    return postMapper.toResponse(postRepository.save(getPost));
  }

  @Override
  public void deletePostById(int id) {

    Post post = checkIfUserAuthority(id);

    postRepository.deleteById(post.getId());
  }

  private Post checkIfUserAuthority(int postId) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RecordNotFoundException("User Not Found"));

    List<Post> posts = user.getPosts();

    return posts.stream()
        .filter(post -> post.getId() == postId)
        .findFirst()
        .orElseThrow(() -> new NotAuthToSeeResourseException("Post Not Found"));
  }
}
