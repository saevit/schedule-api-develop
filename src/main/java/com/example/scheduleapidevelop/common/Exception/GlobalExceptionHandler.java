package com.example.scheduleapidevelop.common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> MethodArgumentNotValidHandler(MethodArgumentNotValidException e){

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

    @ExceptionHandler(NotFoundIdException.class)
    public ResponseEntity<ExceptionDto> NotFoundIdHandler(NotFoundIdException e) {

        ExceptionDto exceptionDto = new ExceptionDto(404, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ExceptionDto> WrongPasswordHandler(WrongPasswordException e) {

        ExceptionDto exceptionDto = new ExceptionDto(401, e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDto);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ExceptionDto> LoginFailedHandler(LoginFailedException e) {

        ExceptionDto exceptionDto = new ExceptionDto(401, e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionDto);
    }
}
