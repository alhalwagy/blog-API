package com.java.blogApp.mapper.implemention;

import com.java.blogApp.dto.category.CategoryRequestModel;
import com.java.blogApp.dto.category.CategoryResponseModel;
import com.java.blogApp.entity.Category;
import com.java.blogApp.mapper.CategoryMapper;
import com.java.blogApp.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {

  private final PostMapper postMapper;

  @Override
  public CategoryResponseModel toResponse(Category category) {
    return CategoryResponseModel.builder()
        .id(category.getId())
        .description(category.getDescription())
        .name(category.getName())
        .build();
  }

  @Override
  public Category toEntity(CategoryRequestModel categoryRequestModel) {
    return Category.builder()
        .name(categoryRequestModel.getName())
        .description(categoryRequestModel.getDescription())
        .createdAt(new Timestamp(System.currentTimeMillis()))
        .updatedAt(new Timestamp(System.currentTimeMillis()))
        .build();
  }
}
