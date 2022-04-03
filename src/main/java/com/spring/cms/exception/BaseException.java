package com.spring.cms.exception;

public abstract class BaseException extends RuntimeException {
    public abstract BaseExceptionType getBaseExceptionType();
}
