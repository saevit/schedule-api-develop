package com.example.scheduleapidevelop.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDeleteRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;
}
