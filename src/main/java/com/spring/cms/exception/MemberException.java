package com.spring.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class MemberException extends BaseException {
    private BaseExceptionType baseExceptionType;

    @Getter
    @AllArgsConstructor
    public enum MemberExceptionType implements BaseExceptionType {
        ALREADY_EXIST_MEMBER(HttpStatus.BAD_REQUEST, null, "이미 존재하는 계정입니다."),
        NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST, null, "회원 정보가 없습니다.");

        private HttpStatus httpStatus;
        private String errorCode;
        private String errorMessage;
    }
}
