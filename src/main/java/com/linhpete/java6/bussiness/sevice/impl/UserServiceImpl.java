package com.linhpete.java6.bussiness.sevice.impl;

import com.linhpete.java6.presentation.request_handler.dto.request.authentication.LogoutRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserPwdUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRegisterRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.UserResponse;
import com.linhpete.java6.bussiness.exception.AppException;
import com.linhpete.java6.presentation.request_handler.mapper.UserMapper;
import com.linhpete.java6.bussiness.sevice.AuthenticationService;
import com.linhpete.java6.bussiness.sevice.UserService;
import com.linhpete.java6.persistence.entity.User;
import com.linhpete.java6.persistence.repository.UserRepository;
import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    AuthenticationService authenticationService;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new AppException(ErrorCode.ENTITY_ALREADY_EXISTS);
        }
        User user = userMapper.mapFromCreationRequest(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse registerUser(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.ENTITY_ALREADY_EXISTS);
        }
        User user = userMapper.mapFromRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("client");
        return userMapper.mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        userMapper.updateEntity(user, request);
        return userMapper.mapToResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse updatePassword(String email, UserPwdUpdateRequest request) throws ParseException, JOSEException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        authenticationService.logOut(LogoutRequest.builder().token(request.getToken()).build());
        return userMapper.mapToResponse(userRepository.save(user));
    }
}
