package com.spring.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ContentsException extends BaseException {

    private BaseExceptionType baseExceptionType;

    @Getter
    @AllArgsConstructor
    public enum ContentsExceptionType implements BaseExceptionType {
        CONTENTS_ID_IS_NULL(HttpStatus.BAD_REQUEST, null, "컨텐츠 ID가 없습니다."),
        CONTENTS_NOT_FOUND(HttpStatus.BAD_REQUEST, null, "컨텐츠 정보를 찾을 수 없습니다.");

        private HttpStatus httpStatus;
        private String errorCode;
        private String errorMessage;
    }
}
