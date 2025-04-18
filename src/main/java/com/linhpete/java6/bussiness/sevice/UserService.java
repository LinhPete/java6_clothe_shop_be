package com.linhpete.java6.bussiness.sevice;

import com.linhpete.java6.persistence.entity.User;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserPwdUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRegisterRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.UserResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
    User getUserByEmail(String email);
    UserResponse createUser(UserRequest userRequest);
    UserResponse registerUser(UserRegisterRequest request);
    UserResponse updateUser(String id, UserUpdateRequest userRequest);
    void deleteUser(String id);

    UserResponse updatePassword(String email, UserPwdUpdateRequest request) throws ParseException, JOSEException;
}
