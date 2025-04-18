package com.linhpete.java6.presentation.api_response.code_enum;

import com.linhpete.java6.presentation.api_response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum SuccessCode implements ResponseCode {

    //COMMON_SUCCESS
    ENTITIES_FOUND(200, "Entities retrieved successfully", HttpStatus.OK),
    ENTITY_FOUND(200, "Entity retrieved successfully", HttpStatus.OK),
    ENTITY_CREATED(201, "Entity created successfully", HttpStatus.CREATED),
    ENTITY_UPDATED(202, "Entity updated successfully", HttpStatus.OK),
    ENTITY_DELETED(203, "Entity deleted successfully", HttpStatus.OK),

    //AUTHENTICATION_API_SUCCESS_CODES
    LOGIN_SUCCESS(50, "Login successful", HttpStatus.OK),
    LOGOUT_SUCCESS(51, "Logout successful", HttpStatus.OK),
    TOKEN_REFRESH_SUCCESS(52, "Token refresh successful", HttpStatus.OK),
    TOKEN_INTROSPECT_SUCCESS(53, "Token introspect successful", HttpStatus.OK),

    //USER_API_SUCCESS_CODES
    USER_CREATED(100, "User created successfully", HttpStatus.CREATED),
    USER_UPDATED(101, "User updated successfully", HttpStatus.OK),
    USER_DELETED(102, "User deleted successfully", HttpStatus.OK),
    USER_RETRIEVED(103, "User retrieved successfully", HttpStatus.OK),
    USERS_RETRIEVED(104, "Users retrieved successfully", HttpStatus.OK),
    PASSWORD_UPDATED(105, "Password updated successfully", HttpStatus.OK),


    ;

    private final int responseCode;
    private final String responseMessage;
    private final HttpStatusCode httpStatusCode;
}
