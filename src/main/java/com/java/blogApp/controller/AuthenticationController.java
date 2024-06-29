package com.java.blogApp.controller;

import com.java.blogApp.dto.authentication.AuthentecationResponseModel;
import com.java.blogApp.dto.authentication.AuthenticationRequestModel;
import com.java.blogApp.dto.authentication.RegisterRequestModel;
import com.java.blogApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthentecationResponseModel> register(
      @RequestBody RegisterRequestModel authRequest) {
    return ResponseEntity.ok(authService.register(authRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthentecationResponseModel> login(
      @RequestBody AuthenticationRequestModel authentecationRequestModel) {

    return ResponseEntity.ok(authService.login(authentecationRequestModel));
  }
}
