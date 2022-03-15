package com.spring.cms.exception;

import lombok.Getter;

@Getter
public class MemberDuplicatedException extends RuntimeException {

    private String email;

    public MemberDuplicatedException(String email) {
        this.email = email;
    }
}
