package com.example.scheduleapidevelop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdatePasswordRequestDto {

    private final String oldPassword;
    private final String newPassword;
}
