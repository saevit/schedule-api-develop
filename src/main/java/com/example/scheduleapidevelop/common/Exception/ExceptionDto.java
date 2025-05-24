package com.example.scheduleapidevelop.common.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionDto {
    private final int status;
    private final String message;
}
