package com.linhpete.java6.presentation.request_handler.mapper;

import com.linhpete.java6.presentation.request_handler.dto.request.user.UserPwdUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRegisterRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.user.UserUpdateRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.UserResponse;
import com.linhpete.java6.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    void updateEntity(@MappingTarget User user, UserUpdateRequest updateRequest);
    void updatePassword(@MappingTarget User user, UserPwdUpdateRequest updateRequest);
    User mapFromRegisterRequest(UserRegisterRequest request);
    User mapFromCreationRequest(UserRequest request);
    UserResponse mapToResponse(User user);
}
