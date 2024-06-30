package com.java.blogApp.service;

import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.dto.tag.TagRequestModel;
import com.java.blogApp.dto.tag.TagResponseModel;

import java.util.List;

public interface TagService {

    List<TagResponseModel> getAllTags();

    TagResponseModel createNewTag(TagRequestModel request);

    void addTagToPost(int postId, int tagId);

    List<PostResponseModel> getAllPostsWithTagId(int id);

    List<PostResponseModel> getAllPostsWithTagName(String name);

}
