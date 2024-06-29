package com.java.blogApp.service;

import com.java.blogApp.dto.authentication.AuthentecationResponseModel;
import com.java.blogApp.dto.authentication.AuthenticationRequestModel;
import com.java.blogApp.dto.authentication.RegisterRequestModel;

public interface AuthService {

  AuthentecationResponseModel register(RegisterRequestModel requestModel);

  AuthentecationResponseModel login(AuthenticationRequestModel requestModel);
}
