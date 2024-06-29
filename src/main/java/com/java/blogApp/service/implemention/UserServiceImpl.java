package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.user.UserRequestModel;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.UserMapper;
import com.java.blogApp.dto.user.UserResponseModel;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userResponseMapper;

  @Override
  public List<UserResponseModel> findAllUsers() {
    List<User> users = userRepository.findAll();

    return users.stream().map(userResponseMapper::toResponse).toList();
  }

  @Override
  public UserResponseModel findUserById(int id) {

    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException("User with id " + id + " not found"));

    return userResponseMapper.toResponse(user);
  }

  @Override
  public UserResponseModel updateUser(UserRequestModel requestModel) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new RecordNotFoundException("User with email " + email + " not found"));

    user.setFirstname(requestModel.getFirstname());
    user.setLastname(requestModel.getLastname());

    userRepository.save(user);

    return userResponseMapper.toResponse(user);
  }

  @Override
  public void deleteAccount() {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();

    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new RecordNotFoundException("User with email " + email + " not found"));

    userRepository.delete(user);
  }

  @Override
  public List<UserResponseModel> searchUserByCritera(String email, String firstname, String lastname) {

    List<User> users = new ArrayList<>();
    if(email != null) {
       users.addAll(userRepository.findAllByEmail(email));

    }else if(firstname != null) {
      users.addAll( userRepository.findAllByFirstname(firstname));

      return users.stream().map(userResponseMapper::toResponse).toList();
    }else if(lastname != null) {
     users.addAll( userRepository.findAllByLastname(lastname));


    }else {
      users.addAll(userRepository.findAll());
    }

    return users.stream().map(userResponseMapper::toResponse).toList();

  }
}
