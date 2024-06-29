package com.java.blogApp.dto.post;

import com.java.blogApp.dto.tag.TagResponseModel;
import com.java.blogApp.dto.user.UserResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseModel {

    private int id;
    private String title;
    private String content;
    private String summary;
    private Timestamp updatedAt;
    private Timestamp publishedAt;
    private int LikesCount;
    private UserResponseModel Author;
    private List<TagResponseModel> tags;

}
