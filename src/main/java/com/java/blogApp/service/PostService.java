package com.java.blogApp.service;

import com.java.blogApp.dto.post.PostRequestModel;
import com.java.blogApp.dto.post.PostResponseModel;

import java.util.List;

public interface PostService {

    List<PostResponseModel> findAll();

    PostResponseModel createPost(PostRequestModel postRequestModel);

    List<PostResponseModel> getMyPosts();

    PostResponseModel updatePost(int id, PostRequestModel postRequestModel);

    void deletePostById(int id);
}
