package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.category.CategoryRequestModel;
import com.java.blogApp.dto.category.CategoryResponseModel;
import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.entity.Category;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.CategoryMapper;
import com.java.blogApp.mapper.PostMapper;
import com.java.blogApp.repository.CategoryRepository;
import com.java.blogApp.repository.PostPagingAndSortingRepository;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.service.CategoryService;
import com.java.blogApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final PostPagingAndSortingRepository postRepository;
  private final UserRepository userRepository;
  private final CategoryMapper categoryMapper;
  private final PostMapper postMapper;

  @Override
  public List<CategoryResponseModel> getAllcategories() {
    return categoryRepository.findAll().stream().map(categoryMapper::toResponse).toList();
  }

  @Override
  public CategoryResponseModel createNewCategory(CategoryRequestModel request) {

    if (categoryRepository.existsByName(request.getName())) {
      throw new NotAuthToSeeResourseException(
          "That Category " + request.getName() + " Is Already exist!");
    }

    Category category = categoryMapper.toEntity(request);

    return categoryMapper.toResponse(categoryRepository.save(category));
  }

  @Override
  public void addPostToCategory(int postId, int categoryId) {
    checkUserAuthToPerformCRUDOnPost(postId);

    Category category =
        categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () ->
                    new RecordNotFoundException(
                        "That Category Id " + categoryId + " Is Not Found!"));

    Post post =
        postRepository
            .findById(postId)
            .orElseThrow(
                () -> new RecordNotFoundException("That Post Id " + postId + " Is Not Found!"));

    if (post.getCategories().contains(category))
      throw new RecordNotFoundException("That Category Contains that Post Already!");

    post.addCategory(category);

    postRepository.save(post);


  }

  @Override
  public List<PostResponseModel> getAllPostsWithCategoryId(int id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new RecordNotFoundException(
                        "That Category Id " + id + " Is Not Found!"));

    return category.getPosts().stream()
            .map(postMapper::toResponse)
            .toList();
  }

  @Override
  public List<PostResponseModel> getAllPostsWithCategoryName(String name) {
    Category category = categoryRepository.findByName(name)
            .orElseThrow(()-> new RecordNotFoundException("That Category Name " + name + " Is Not Found!"));

    return category.getPosts().stream()
            .map(postMapper::toResponse)
            .toList();
  }

  private void checkUserAuthToPerformCRUDOnPost(int postId){
    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RecordNotFoundException("User Email " + email + " Not Found!"));

    Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RecordNotFoundException("That Post Id " + postId + " Is Not Found!"));

    if(post.getUser().getId() != user.getId())
      throw new NotAuthToSeeResourseException("You are Not Auth to Do Add That Post To Any Category");
  }
}
