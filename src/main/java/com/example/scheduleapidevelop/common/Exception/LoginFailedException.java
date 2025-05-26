package com.example.scheduleapidevelop.common.Exception;

/**
 * 로그인 실패 예외
 * (이메일과 비밀번호로 유저가 조회되지 않는 경우 발생하는 예외)
 */
public class LoginFailedException extends RuntimeException {

    public LoginFailedException() {
        super("로그인에 실패하였습니다. 이메일 또는 비밀번호가 일치하지 않습니다.");
    }
}
