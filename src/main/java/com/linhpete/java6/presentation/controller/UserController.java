package com.linhpete.java6.presentation.controller;

import com.linhpete.java6.presentation.request_handler.UserRequestHandler;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserPwdUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRegisterRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.UserResponse;
import com.linhpete.java6.bussiness.sevice.UserService;
import com.linhpete.java6.presentation.api_response.APIResponse;
import com.linhpete.java6.presentation.api_response.ResponseEntityFactory;
import com.linhpete.java6.presentation.api_response.code_enum.SuccessCode;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserRequestHandler userRequestHandler;
    @GetMapping
    ResponseEntity<APIResponse<List<UserResponse>>> getAllUsers(){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITIES_FOUND,
                userRequestHandler.getAllUsers()
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<UserResponse>> getUserById(@PathVariable("id") String id){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_FOUND,
                userRequestHandler.getUserById(id)
        );
    }
    @GetMapping("/my-info")
    ResponseEntity<APIResponse<UserResponse>> getMyInfo() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("{} : get profile", authentication.getName());
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_FOUND,
                userRequestHandler.getMyInfo(authentication)
        );
    }
    @PostMapping
    ResponseEntity<APIResponse<UserResponse>> createUser(@RequestBody @Valid UserRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_CREATED,
                userService.createUser(request)
        );
    }
    @PostMapping("/register")
    ResponseEntity<APIResponse<UserResponse>> registerUser(@RequestBody @Valid UserRegisterRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_CREATED,
                userService.registerUser(request)
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<APIResponse<UserResponse>> updateUser(@PathVariable("id") String id, @RequestBody @Valid UserUpdateRequest request){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_UPDATED,
                userService.updateUser(id, request)
        );
    }
    @PutMapping("/change-password")
    ResponseEntity<APIResponse<UserResponse>> updatePassword(@RequestBody @Valid UserPwdUpdateRequest request) throws ParseException, JOSEException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_UPDATED,
                userService.updatePassword(authentication.getName(), request)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.ENTITY_DELETED,
                null
        );
    }
}
