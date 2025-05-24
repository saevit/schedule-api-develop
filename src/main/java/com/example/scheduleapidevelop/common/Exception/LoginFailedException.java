package com.example.scheduleapidevelop.common.Exception;

public class LoginFailedException extends RuntimeException{

    public LoginFailedException() {
        super("로그인에 실패하였습니다. 이메일 또는 비밀번호가 일치하지 않습니다.");
    }
}
