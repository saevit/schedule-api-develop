package com.example.scheduleapidevelop.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdatePasswordRequestDto {

    @NotBlank(message = "기존 비밀번호를 입력해주세요.")
    private final String oldPassword;
    @NotBlank(message = "새로운 비밀번호를 입력해주세요.")
    @Size(min = 6, message = "비밀번호는 6글자 이상이어야 합니다.")
    private final String newPassword;
}
