package com.linhpete.java6.presentation.api_response;

import org.springframework.http.HttpStatusCode;

public interface ResponseCode {
    int getResponseCode();
    String getResponseMessage();
    HttpStatusCode getHttpStatusCode();
}
