package com.example.scheduleapidevelop.service;

import com.example.scheduleapidevelop.model.dto.ScheduleCreateRequestDto;
import com.example.scheduleapidevelop.model.dto.ScheduleResponseDto;
import com.example.scheduleapidevelop.model.dto.ScheduleUpdateRequestDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto save(ScheduleCreateRequestDto requestDto);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findById(Long id);

    ScheduleResponseDto update(Long id, ScheduleUpdateRequestDto requestDto);

    void delete(Long id);
}
