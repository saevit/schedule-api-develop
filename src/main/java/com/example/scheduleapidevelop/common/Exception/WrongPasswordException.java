package com.example.scheduleapidevelop.common.Exception;

/**
 * 비밀번호가 일치하지 않을 때 발생하는 예외
 */
public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
