package com.java.blogApp.mapper;

import com.java.blogApp.dto.post.PostRequestModel;
import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.entity.Post;
import com.java.blogApp.entity.User;

public interface PostMapper {

    PostResponseModel toResponse(Post post);

    Post toEntity(PostRequestModel postRequestModel, User user);
}


