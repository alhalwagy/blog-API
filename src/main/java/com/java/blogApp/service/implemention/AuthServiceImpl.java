package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.authentication.AuthentecationResponseModel;
import com.java.blogApp.dto.authentication.AuthenticationRequestModel;
import com.java.blogApp.dto.authentication.RegisterRequestModel;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.AuthenticationMapper;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.security.JwtService;
import com.java.blogApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final AuthenticationMapper authenticationMapper;

  @Override
  public AuthentecationResponseModel register(RegisterRequestModel requestModel) {

    if (userRepository.existsByEmail(requestModel.getEmail())) {
      throw new NotAuthToSeeResourseException("Email or phone is already in use");
    }

    User user = authenticationMapper.toEntity(requestModel);

    userRepository.save(user);

    return authenticationMapper.toAuthResponse(jwtService.generateToken(user));
  }

  @Override
  public AuthentecationResponseModel login(AuthenticationRequestModel requestModel) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            requestModel.getEmail(), requestModel.getPassword()));

    User user =
        userRepository
            .findByEmail(requestModel.getEmail())
            .orElseThrow(() -> new RecordNotFoundException("email or phone not found"));
    System.out.println(user.toString());

    String token = jwtService.generateToken(user);

    System.out.println(token);
    return authenticationMapper.toAuthResponse(token);
  }
}
