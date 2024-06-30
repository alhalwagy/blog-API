package com.java.blogApp.mapper;

import com.java.blogApp.dto.category.CategoryRequestModel;
import com.java.blogApp.dto.category.CategoryResponseModel;
import com.java.blogApp.entity.Category;

public interface CategoryMapper {

    CategoryResponseModel toResponse(Category category);

    Category toEntity(CategoryRequestModel categoryRequestModel);

}
