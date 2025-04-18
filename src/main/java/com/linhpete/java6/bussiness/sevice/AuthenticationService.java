package com.linhpete.java6.bussiness.sevice;

import com.linhpete.java6.presentation.request_handler.dto.request.authentication.IntrospectRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.LoginRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.LogoutRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.RefreshTokenRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.authentication.LoginResponse;
import com.linhpete.java6.presentation.request_handler.dto.response.authentication.RefreshTokenResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    LoginResponse logIn(LoginRequest loginRequest);
    void logOut(LogoutRequest logoutRequest) throws ParseException, JOSEException;
    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException;
    void introspect(IntrospectRequest introspectRequest) throws ParseException, JOSEException;
}
