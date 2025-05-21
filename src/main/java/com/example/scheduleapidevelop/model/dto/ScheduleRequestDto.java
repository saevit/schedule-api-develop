package com.example.scheduleapidevelop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private String user;
    private String title;
    private String content;
}
