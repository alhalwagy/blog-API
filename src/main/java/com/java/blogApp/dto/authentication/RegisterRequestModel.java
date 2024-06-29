package com.java.blogApp.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestModel {

  private String email;

  private String password;

  private String firstname;

  private String lastname;
}
