package com.linhpete.java6.bussiness.sevice.impl;

import com.linhpete.java6.presentation.request_handler.dto.request.authentication.IntrospectRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.LoginRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.LogoutRequest;
import com.linhpete.java6.presentation.request_handler.dto.request.authentication.RefreshTokenRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.authentication.LoginResponse;
import com.linhpete.java6.presentation.request_handler.dto.response.authentication.RefreshTokenResponse;
import com.linhpete.java6.bussiness.exception.AppException;
import com.linhpete.java6.bussiness.sevice.AuthenticationService;
import com.linhpete.java6.persistence.entity.InvalidatedToken;
import com.linhpete.java6.persistence.entity.User;
import com.linhpete.java6.persistence.repository.InvalidatedTokenRepository;
import com.linhpete.java6.persistence.repository.UserRepository;
import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    @Value("${jwt.signer-key}")
    protected String signerKey;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long validDuration;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long refreshDuration;

    @Override
    public LoginResponse logIn(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.WRONG_ACCOUNT));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!matches) {
            throw new AppException(ErrorCode.WRONG_ACCOUNT);
        }
        return LoginResponse.builder()
                .token(generateToken(user))
                .build();
    }

    @Override
    public void logOut(LogoutRequest logoutRequest) throws ParseException, JOSEException {
        var token = verifyToken(logoutRequest.getToken(), false).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        String jit = token.getJWTClaimsSet().getJWTID();
        Date expiryTime = token.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryDate(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws ParseException, JOSEException {
        var signedJWT = verifyToken(refreshTokenRequest.getToken(), true).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryDate(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
        String email = signedJWT.getJWTClaimsSet().getSubject();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.ENTITY_NOT_FOUND));
        return RefreshTokenResponse.builder()
                .token(generateToken(user))
                .build();
    }

    @Override
    public void introspect(IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var token = introspectRequest.getToken();
        verifyToken(token, false).orElseThrow(() -> new AppException(ErrorCode.INVALID_TOKEN));
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("clothe_shop")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(validDuration, ChronoUnit.MINUTES).toEpochMilli()
                ))
                .claim("scope", user.getRole())
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Generate token failed", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_ERROR);
        }
    }

    private Optional<SignedJWT> verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        boolean isInvalidatedToken = invalidatedTokenRepository.existsById(jit);
        Date expiration = isRefresh ?
                new Date(
                        signedJWT.getJWTClaimsSet().getIssueTime()
                                .toInstant().plus(refreshDuration, ChronoUnit.DAYS).toEpochMilli()
                ) :
                signedJWT.getJWTClaimsSet().getExpirationTime();
        if (!isInvalidatedToken && signedJWT.verify(verifier) && expiration.after(new Date())) {
            return Optional.of(signedJWT);
        }
        return Optional.empty();
    }

}
