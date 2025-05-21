package com.example.scheduleapidevelop.service;

import com.example.scheduleapidevelop.model.dto.ScheduleRequestDto;
import com.example.scheduleapidevelop.model.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto save(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findById(Long id);

    ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto);

    void delete(Long id);
}
