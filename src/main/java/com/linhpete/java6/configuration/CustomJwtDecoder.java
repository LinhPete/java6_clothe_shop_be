package com.linhpete.java6.configuration;

import com.linhpete.java6.presentation.request_handler.dto.request.authentication.IntrospectRequest;
import com.linhpete.java6.bussiness.exception.AppException;
import com.linhpete.java6.bussiness.sevice.AuthenticationService;
import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signer-key}")
    private String signerKey;
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    private final AuthenticationService authenticationService;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            authenticationService.introspect(IntrospectRequest.builder()
                    .token(token)
                    .build());
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.UNCATEGORIZED_ERROR);
        }
        if (Objects.isNull(nimbusJwtDecoder)){
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder
                    .withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
