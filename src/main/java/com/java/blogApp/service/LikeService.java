package com.java.blogApp.service;

import com.java.blogApp.dto.like.LikeResponseModel;

import java.util.List;

public interface LikeService {


    LikeResponseModel findById(int id);

    LikeResponseModel createLike(int postId);

    void deleteLike(int postId);

    List<LikeResponseModel> getLikesByPostId(int postId);

}
