package com.spring.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class AuthorityException extends BaseException {
    private BaseExceptionType baseExceptionType;

    @Getter
    @AllArgsConstructor
    public enum AuthorityExceptionType implements BaseExceptionType {
        NOT_EXIST_AUTHORITY(HttpStatus.BAD_REQUEST, null, "권한이 존재하지 않습니다.");

        private HttpStatus httpStatus;
        private String errorCode;
        private String errorMessage;
    }
}
