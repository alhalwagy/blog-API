package com.java.blogApp.mapper;

import com.java.blogApp.dto.tag.TagRequestModel;
import com.java.blogApp.dto.tag.TagResponseModel;
import com.java.blogApp.entity.Tag;

public interface TagMapper {

    TagResponseModel toResponse(Tag tag);
    Tag toEntity(TagRequestModel tagRequestModel);

}
