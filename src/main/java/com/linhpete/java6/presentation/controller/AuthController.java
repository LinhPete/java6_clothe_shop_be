package com.linhpete.java6.presentation.controller;

import com.linhpete.java6.presentation.request_handler.dto.request.authentication.IntrospectRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.LoginRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.LogoutRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.RefreshTokenRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.authentication.LoginResponse;
import com.linhpete.java6.presentation.request_handler.dto.response.authentication.RefreshTokenResponse;
import com.linhpete.java6.bussiness.sevice.AuthenticationService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthenticationService authenticationService;
    @PostMapping("/log-in")
    ResponseEntity<APIResponse<LoginResponse>> logIn(@RequestBody @Valid LoginRequest logInRequest){
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.LOGIN_SUCCESS,
                authenticationService.logIn(logInRequest)
        );
    }
    @PostMapping("/log-out")
    ResponseEntity<APIResponse<Void>> logOut(@RequestBody LogoutRequest logOutRequest) throws ParseException, JOSEException {
        authenticationService.logOut(logOutRequest);
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.LOGOUT_SUCCESS,
                null
        );
    }
    @PostMapping("/refresh")
    ResponseEntity<APIResponse<RefreshTokenResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException {
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.TOKEN_REFRESH_SUCCESS,
                authenticationService.refreshToken(refreshTokenRequest)
        );
    }
    @PostMapping("/introspect")
    ResponseEntity<APIResponse<Void>> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        authenticationService.introspect(introspectRequest);
        return ResponseEntityFactory.getResponseEntity(
                SuccessCode.TOKEN_INTROSPECT_SUCCESS,
                null
        );
    }
}
