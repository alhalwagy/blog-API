package com.java.blogApp.mapper.implemention;

import com.java.blogApp.dto.tag.TagRequestModel;
import com.java.blogApp.dto.tag.TagResponseModel;
import com.java.blogApp.entity.Tag;
import com.java.blogApp.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class TagMapperImpl implements TagMapper {

  @Override
  public TagResponseModel toResponse(Tag tag) {
    return TagResponseModel.builder()
        .id(tag.getId())
        .name(tag.getName())
        .description(tag.getDescription())
        .build();
  }

  @Override
  public Tag toEntity(TagRequestModel tagRequestModel) {
    return Tag.builder()
        .name(tagRequestModel.getName())
        .description(tagRequestModel.getDescription())
        .createdAt(new Timestamp(System.currentTimeMillis()))
        .updatedAt(new Timestamp(System.currentTimeMillis()))
        .build();
  }
}
