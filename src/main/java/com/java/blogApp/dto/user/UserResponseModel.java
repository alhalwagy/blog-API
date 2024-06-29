package com.java.blogApp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseModel {

  private int id;
  private String firstname;

  private String lastname;

  private String email;

  private Timestamp createdAt;

  private Timestamp updatedAt;
}
