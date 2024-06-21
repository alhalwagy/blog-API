package com.java.blogApp.service;

import com.java.blogApp.dto.AuthentecationResponseModel;
import com.java.blogApp.dto.AuthenticationRequestModel;
import com.java.blogApp.dto.RegisterRequestModel;

public interface AuthService {

    AuthentecationResponseModel register(RegisterRequestModel requestModel);

    AuthentecationResponseModel login(AuthenticationRequestModel requestModel);

}
