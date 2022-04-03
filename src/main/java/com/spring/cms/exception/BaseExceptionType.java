package com.spring.cms.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    HttpStatus getHttpStatus();
    String getErrorCode();
    String getErrorMessage();
}
