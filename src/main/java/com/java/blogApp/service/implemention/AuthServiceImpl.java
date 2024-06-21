package com.java.blogApp.service.implemention;

import com.java.blogApp.dto.AuthentecationResponseModel;
import com.java.blogApp.dto.AuthenticationRequestModel;
import com.java.blogApp.dto.RegisterRequestModel;
import com.java.blogApp.entity.User;
import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.mapper.AuthenticationMapper;
import com.java.blogApp.repository.UserRepository;
import com.java.blogApp.security.CustomUserDetails;
import com.java.blogApp.security.JwtService;
import com.java.blogApp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

        if(userRepository.existsByEmail(requestModel.getEmail())){
            throw new NotAuthToSeeResourseException("Email or phone is already in use");
        }

        User user = authenticationMapper.toEntity(requestModel);

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        userRepository.save(user);

        return authenticationMapper.toAuthResponse(jwtService.generateToken( customUserDetails));

    }

    @Override
    public AuthentecationResponseModel login(AuthenticationRequestModel requestModel) {

        System.out.println("yessssssssssssssssssssssssssssss");
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword())
//            );

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestModel.getEmail(), requestModel.getPassword())
            );
        } catch (AuthenticationException e) {
            System.err.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid credentials", e);
        }

        User user = userRepository.findByEmail(requestModel.getEmail()).orElseThrow(()-> new RecordNotFoundException("email or phone not found"));
        System.out.println(user.toString());

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(customUserDetails);

        System.out.println(token);
        return authenticationMapper.toAuthResponse(token);


    }
}
