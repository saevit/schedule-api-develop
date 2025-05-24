package com.example.scheduleapidevelop.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignUpRequestDto {

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    @Size(max = 4, message = "이름은 4글자 이하여야 합니다.")
    private final String name;
    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "유효한 이메일 주소를 입력하세요.")
    private final String email;
    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 6, message = "비밀번호는 6글자 이상이어야 합니다.")
    private final String password;
}
