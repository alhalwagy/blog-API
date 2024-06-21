
package com.java.blogApp.service;

import com.java.blogApp.dto.UserResponseModel;

import java.util.List;

public interface UserService {

    List<UserResponseModel> findAllUsers();

}
