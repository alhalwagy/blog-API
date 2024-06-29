package com.java.blogApp.service;

import com.java.blogApp.dto.user.UserRequestModel;
import com.java.blogApp.dto.user.UserResponseModel;

import java.util.List;

public interface UserService {

  List<UserResponseModel> findAllUsers();

  UserResponseModel findUserById(int id);

  UserResponseModel updateUser(UserRequestModel requestModel);

  void deleteAccount();

  List<UserResponseModel> searchUserByCritera(String email, String firstname, String lastname);
}
