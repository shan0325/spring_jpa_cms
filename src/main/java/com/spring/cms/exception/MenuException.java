package com.spring.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class MenuException extends BaseException {
    private BaseExceptionType baseExceptionType;

    @Getter
    @AllArgsConstructor
    public enum MenuExceptionType implements BaseExceptionType {
        NOT_FOUND_PARENT_MENU(HttpStatus.BAD_REQUEST, null,"상위 메뉴 정보가 없습니다."),
        NOT_FOUND_TOP_MENU(HttpStatus.BAD_REQUEST, null,"최상위 메뉴 정보가 없습니다."),
        NOT_FOUND_MENU(HttpStatus.BAD_REQUEST, null,"메뉴 정보가 없습니다."),
        CHILD_MENU_EXISTS_CANNOT_DELETE(HttpStatus.BAD_REQUEST, null,"하위 메뉴가 존재하여 삭제할 수 없습니다.");

        private HttpStatus httpStatus;
        private String errorCode;
        private String errorMessage;
    }
}
