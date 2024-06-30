package com.java.blogApp.dto.like;

import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.dto.user.UserResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponseModel {

    private int id;

    private UserResponseModel userResponseModel;

    private PostResponseModel postResponseModel;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
