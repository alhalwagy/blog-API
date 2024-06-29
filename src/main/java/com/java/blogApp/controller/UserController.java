package com.java.blogApp.controller;

import com.java.blogApp.dto.user.UserRequestModel;
import com.java.blogApp.dto.user.UserResponseModel;
import com.java.blogApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("")
  public List<UserResponseModel> findAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("{id}")
  public UserResponseModel findUserById(@PathVariable int id) {

    return userService.findUserById(id);
  }

  @PutMapping("")
  public UserResponseModel updateUserById(@RequestBody UserRequestModel requestModel) {

    return userService.updateUser(requestModel);
  }

  @DeleteMapping("")
  public void deleteAccount() {

    userService.deleteAccount();
  }

  @GetMapping(value = "/search")
  public List<UserResponseModel> searchUserByCritera(@RequestParam(required = false) String email,
                                                     @RequestParam(required = false) String firstname,
                                                     @RequestParam(required = false) String lastname) {

    return userService.searchUserByCritera(email,firstname,lastname);

  }

}
