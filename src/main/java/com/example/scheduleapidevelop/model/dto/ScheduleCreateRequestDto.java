package com.example.scheduleapidevelop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {

    private final Long userId;
    private final String title;
    private final String content;
}
