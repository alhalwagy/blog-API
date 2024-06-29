package com.java.blogApp.mapper.implemention;

import com.java.blogApp.entity.User;
import com.java.blogApp.entity.enums.Role;
import com.java.blogApp.mapper.AuthenticationMapper;
import com.java.blogApp.dto.authentication.AuthentecationResponseModel;
import com.java.blogApp.dto.authentication.RegisterRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class AuthenticationMapperImpl implements AuthenticationMapper {

  private final PasswordEncoder passwordEncoder;

  @Override
  public User toEntity(RegisterRequestModel requestModel) {
    return User.builder()
        .firstname(requestModel.getFirstname())
        .lastname(requestModel.getLastname())
        .email(requestModel.getEmail())
        .password(passwordEncoder.encode(requestModel.getPassword()))
        .role(Role.USER)
        .createdAt(new Timestamp(System.currentTimeMillis()))
        .updatedAt(new Timestamp(System.currentTimeMillis()))
        .build();
  }

  @Override
  public AuthentecationResponseModel toAuthResponse(String token) {
    return AuthentecationResponseModel.builder().token(token).build();
  }
}
