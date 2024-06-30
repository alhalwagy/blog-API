package com.java.blogApp.controller;

import com.java.blogApp.dto.category.CategoryRequestModel;
import com.java.blogApp.dto.category.CategoryResponseModel;
import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("")
  public List<CategoryResponseModel> getAllCategories() {

    return categoryService.getAllcategories();
  }

  @PostMapping("")
  public CategoryResponseModel createCategory(
      @RequestBody CategoryRequestModel categoryRequestModel) {

    return categoryService.createNewCategory(categoryRequestModel);
  }

  @PostMapping("/{postId}/{categoryId}")
  public void addCategoryForPost(@PathVariable int postId, @PathVariable int categoryId) {
    categoryService.addPostToCategory(postId, categoryId);
  }

  @GetMapping(params = "id")
  public List<PostResponseModel> getAllPostsWithCategoryId(@RequestParam(name = "id")int id){
    return categoryService.getAllPostsWithCategoryId(id);
  }

  @GetMapping(params = "name")
  public List<PostResponseModel> getAllPostsWithCategoryName(@RequestParam(name = "name")String name){
    return categoryService.getAllPostsWithCategoryName(name);
  }
}
