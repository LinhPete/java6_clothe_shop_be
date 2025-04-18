package com.linhpete.java6.bussiness.exception;

import com.linhpete.java6.presentation.api_response.code_enum.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    public AppException(ErrorCode errorCode) {
        super(errorCode.getResponseMessage());
        this.errorCode = errorCode;
    }
}
