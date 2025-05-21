package com.example.scheduleapidevelop.model.dto;

import com.example.scheduleapidevelop.model.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String user;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto( schedule.getId(),
                                        schedule.getUser(),
                                        schedule.getTitle(),
                                        schedule.getContent(),
                                        schedule.getCreatedAt(),
                                        schedule.getUpdatedAt() );
    }
}
