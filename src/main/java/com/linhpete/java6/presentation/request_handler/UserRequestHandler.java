package com.linhpete.java6.presentation.request_handler;

import com.linhpete.java6.bussiness.sevice.UserService;
import com.linhpete.java6.presentation.api_response.APIResponse;
import com.linhpete.java6.presentation.api_response.ResponseEntityFactory;
import com.linhpete.java6.presentation.api_response.code_enum.SuccessCode;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserPwdUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRegisterRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.UserResponse;
import com.linhpete.java6.presentation.request_handler.mapper.UserMapper;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRequestHandler {
    UserService userService;
    UserMapper userMapper;
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers().stream().map(userMapper::mapToResponse).toList();
    }

    public UserResponse getUserById(String id){
        return userMapper.mapToResponse(userService.getUserById(id));
    }

    public UserResponse getMyInfo(Authentication authentication) {
        return userMapper.mapToResponse(
                userService.getUserByEmail(authentication.getName())
        );
    }

    public ResponseEntity<APIResponse<UserResponse>> createUser(UserRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_CREATED,
                userService.createUser(request)
        );
    }

    public ResponseEntity<APIResponse<UserResponse>> registerUser(UserRegisterRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_CREATED,
                userService.registerUser(request)
        );
    }

    public ResponseEntity<APIResponse<UserResponse>> updateUser(String id, UserUpdateRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_UPDATED,
                userService.updateUser(id, request)
        );
    }

    public ResponseEntity<APIResponse<UserResponse>> updatePassword(UserPwdUpdateRequest request) throws ParseException, JOSEException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_UPDATED,
                userService.updatePassword(authentication.getName(), request)
        );
    }

    public ResponseEntity<APIResponse<Void>> deleteUser(String id){
        userService.deleteUser(id);
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_DELETED,
                null
        );
    }
}
