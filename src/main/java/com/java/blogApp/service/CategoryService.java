package com.java.blogApp.service;

import com.java.blogApp.dto.category.CategoryRequestModel;
import com.java.blogApp.dto.category.CategoryResponseModel;
import com.java.blogApp.dto.post.PostResponseModel;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseModel> getAllcategories();

    CategoryResponseModel createNewCategory(CategoryRequestModel request);

    void addPostToCategory(int postId, int categoryId);

    List<PostResponseModel> getAllPostsWithCategoryId(int id);

    List<PostResponseModel> getAllPostsWithCategoryName(String name);

}
