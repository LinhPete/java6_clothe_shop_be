package com.linhpete.java6.presentation.api_response.code_enum;

import com.linhpete.java6.presentation.api_response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ResponseCode {

    UNIMPLEMENTED_ERROR(666, "Unimplemented functionality", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED_ERROR(999,"Unknown error", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_ENTITIES_FOUND(777,"No records found", HttpStatus.NOT_FOUND),
    ENTITY_NOT_FOUND(999,"Entity not found", HttpStatus.NOT_FOUND),
    ENTITY_ALREADY_EXISTS(999,"Entity already exists", HttpStatus.CONFLICT),

    //VALIDATION_ERRORS
    BLANK_FIELDS(401, "This field cannot be empty", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(402,"Invalid email", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(403,"Password must be between 8 and 16 characters, contain at least 1 normal character, 1 capitalized, 1 number and 1 special character", HttpStatus.BAD_REQUEST),
    INVALID_NAME(404, "Invalid name", HttpStatus.BAD_REQUEST),
    INVALID_PHONE(405, "Phone must be 10 character", HttpStatus.BAD_REQUEST),
    INVALID_DOB(406, "Age must be at least {min}", HttpStatus.BAD_REQUEST),
    INVALID_NUMBER(407, "Invalid number", HttpStatus.BAD_REQUEST),

    //AUTHENTICATION_ERRORS
    WRONG_ACCOUNT(408,"Wrong email or password", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD(409, "Wrong password", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(410,"Must login to continue", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(411,"Unauthorized client", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(412,"Invalid token", HttpStatus.UNAUTHORIZED),



    ;

    private final int responseCode;
    private final String responseMessage;
    private final HttpStatusCode httpStatusCode;
}
