package com.example.scheduleapidevelop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequestDto {

    private final String email;
    private final String password;
}
