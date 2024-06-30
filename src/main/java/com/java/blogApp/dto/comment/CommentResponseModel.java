package com.java.blogApp.dto.comment;

import com.java.blogApp.dto.post.PostResponseModel;
import com.java.blogApp.dto.user.UserResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseModel {

    private int id;

    private UserResponseModel author;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private PostResponseModel post;


}
