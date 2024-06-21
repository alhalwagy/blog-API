package com.java.blogApp.service.implemention;
import com.java.blogApp.entity.User;
import com.java.blogApp.mapper.UserResponseMapper;
import com.java.blogApp.dto.UserResponseModel;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    private final UserResponseMapper userResponseMapper;

    @Override
    public List<UserResponseModel> findAllUsers() {
        List<User> users = userRepository.findAll();

       return users.stream().map(userResponseMapper::toResponse).toList();
    }
}
