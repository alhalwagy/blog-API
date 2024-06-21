package com.java.blogApp.controller;

import com.java.blogApp.dto.UserResponseModel;
import com.java.blogApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @GetMapping("")
    public List<UserResponseModel> findAllUsers(){
        return userService.findAllUsers();
    }


}
