package com.example.scheduleapidevelop.model.dto;

import com.example.scheduleapidevelop.model.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private final Long id;
    private final String name;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto( schedule.getId(),
                                        schedule.getUser().getName(),
                                        schedule.getTitle(),
                                        schedule.getContent(),
                                        schedule.getCreatedAt(),
                                        schedule.getUpdatedAt() );
    }
}
