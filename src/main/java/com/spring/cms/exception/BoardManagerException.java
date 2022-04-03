package com.spring.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BoardManagerException extends BaseException {
    private BaseExceptionType baseExceptionType;

    @Getter
    @AllArgsConstructor
    public enum BoardManagerExceptionType implements BaseExceptionType {
        BOARD_MANAGER_ID_IS_NULL(HttpStatus.BAD_REQUEST, null, "게시판 ID가 없습니다."),
        BOARD_MANAGER_NOT_FOUND(HttpStatus.BAD_REQUEST, null, "게시판 정보를 찾을 수 없습니다.");

        private HttpStatus httpStatus;
        private String errorCode;
        private String errorMessage;
    }
}
