package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.dto.tag.TagRequestModel;
import com.java.blogApp.dto.tag.TagResponseModel;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.Tag;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.PostMapper;
import com.java.blogApp.mapper.TagMapper;
import com.java.blogApp.repository.PostPagingAndSortingRepository;
import com.java.blogApp.repository.TagRepository;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;
  private final PostPagingAndSortingRepository postRepository;
  private final UserRepository userRepository;
  private final TagMapper tagMapper;
  private final PostMapper postMapper;

  @Override
  public List<TagResponseModel> getAllTags() {
    return tagRepository.findAll().stream().map(tagMapper::toResponse).toList();
  }

  @Override
  public TagResponseModel createNewTag(TagRequestModel request) {
    if (tagRepository.existsByName(request.getName())) {
      throw new NotAuthToSeeResourseException(
          "That Tag " + request.getName() + " Is Already exist!");
    }

    Tag tag = tagMapper.toEntity(request);

    return tagMapper.toResponse(tagRepository.save(tag));
  }

  @Override
  public void addTagToPost(int postId, int tagId) {
    checkUserAuthToPerformCRUDOnPost(postId);

    Tag tag =
        tagRepository
            .findById(tagId)
            .orElseThrow(
                () -> new RecordNotFoundException("That Tag Id " + tagId + " Is Not Found!"));

    Post post =
        postRepository
            .findById(postId)
            .orElseThrow(
                () -> new RecordNotFoundException("That Post Id " + postId + " Is Not Found!"));

    if (post.getTags().contains(tag))
      throw new RecordNotFoundException("That post Contains that Tag Already!");

    post.addTag(tag);
    postRepository.save(post);
  }

  @Override
  public List<PostResponseModel> getAllPostsWithTagId(int tagId) {
    Tag tag =
        tagRepository
            .findById(tagId)
            .orElseThrow(
                () -> new RecordNotFoundException("That Tag Id " + tagId + " Is Not Found!"));

    return tag.getPosts().stream().map(postMapper::toResponse).toList();
  }

  @Override
  public List<PostResponseModel> getAllPostsWithTagName(String tagName) {
    Tag tag =
        tagRepository
            .findByName(tagName)
            .orElseThrow(
                () -> new RecordNotFoundException("That Tag Name " + tagName + " Is Not Found!"));

    return tag.getPosts().stream().map(postMapper::toResponse).toList();
  }

  private void checkUserAuthToPerformCRUDOnPost(int postId) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new RecordNotFoundException("User Email " + email + " Not Found!"));

    Post post =
        postRepository
            .findById(postId)
            .orElseThrow(
                () -> new RecordNotFoundException("That Post Id " + postId + " Is Not Found!"));

    if (post.getUser().getId() != user.getId())
      throw new NotAuthToSeeResourseException("You are Not Auth to Do Add Tag To That Post");
  }
}
