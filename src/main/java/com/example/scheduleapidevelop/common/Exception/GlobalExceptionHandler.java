package com.example.scheduleapidevelop.common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Validation Bean 예외처리
     * @return 400 오류필드 : 오류메시지
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> MethodArgumentNotValidHandler(MethodArgumentNotValidException e) {

        StringBuilder stringBuilder = new StringBuilder();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            stringBuilder.append(error.getField());
            stringBuilder.append(": ");
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append("; ");
        }

        ExceptionDto exceptionDto = new ExceptionDto(400, stringBuilder.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    /**
     * id로 조회할 수 없을 때 발생하는 예외 처리
     * @return 404 "해당 " + name + "(id: " + id + ") 을(를) 조회 할 수 없습니다."
     */
    @ExceptionHandler(NotFoundIdException.class)
    public ResponseEntity<ExceptionDto> NotFoundIdHandler(NotFoundIdException e) {

        ExceptionDto exceptionDto = new ExceptionDto(404, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }

    /**
     * 비밀번호가 일치하지 않을 때 발생하는 예외 처리
     * @return 401 "비밀번호가 일치하지 않습니다."
     */
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ExceptionDto> WrongPasswordHandler(WrongPasswordException e) {

        ExceptionDto exceptionDto = new ExceptionDto(401, e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDto);
    }

    /**
     * 로그인 실패 예외 처리
     * @return 401 "로그인에 실패하였습니다. 이메일 또는 비밀번호가 일치하지 않습니다."
     */
    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ExceptionDto> LoginFailedHandler(LoginFailedException e) {

        ExceptionDto exceptionDto = new ExceptionDto(401, e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDto);
    }
}
