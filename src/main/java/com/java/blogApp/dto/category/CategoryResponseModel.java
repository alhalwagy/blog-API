package com.java.blogApp.dto.category;

import com.java.blogApp.dto.post.PostResponseModel;
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
public class CategoryResponseModel {

    private int id;

    private String name;

    private String description;


}
