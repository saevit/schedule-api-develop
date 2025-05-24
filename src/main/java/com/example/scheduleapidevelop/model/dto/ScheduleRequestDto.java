package com.example.scheduleapidevelop.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    @Size(max = 10, message = "제목은 10자 이하로 입력해주세요.")
    private final String title;

    @NotBlank(message = "내용은 비워둘 수 없습니다.")
    private final String content;
}
