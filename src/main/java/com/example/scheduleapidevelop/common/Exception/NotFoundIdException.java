package com.example.scheduleapidevelop.common.Exception;

/**
 * id로 조회할 수 없을 때 발생하는 예외
 */
public class NotFoundIdException extends RuntimeException {

    public NotFoundIdException(String name, Long id) {
        super("해당 " + name + "(id: " + id + ") 을(를) 조회 할 수 없습니다.");
    }
}
