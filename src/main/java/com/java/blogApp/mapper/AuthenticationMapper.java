package com.java.blogApp.mapper;

import com.java.blogApp.entity.User;
import com.java.blogApp.dto.AuthentecationResponseModel;
import com.java.blogApp.dto.RegisterRequestModel;

public interface AuthenticationMapper {
    User toEntity(RegisterRequestModel requestModel);
    AuthentecationResponseModel toAuthResponse(String token);


}
