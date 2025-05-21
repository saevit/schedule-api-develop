package com.example.scheduleapidevelop.service;

import com.example.scheduleapidevelop.model.dto.ScheduleRequestDto;
import com.example.scheduleapidevelop.model.dto.ScheduleResponseDto;
import com.example.scheduleapidevelop.model.entity.Schedule;
import com.example.scheduleapidevelop.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImp implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    @Override
    public ScheduleResponseDto save(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule( requestDto.getUser(),
                                          requestDto.getTitle(),
                                          requestDto.getContent() );

        return ScheduleResponseDto.toDto(scheduleRepository.save(schedule));
    }

    // 전체 일정 조회
    @Override
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                                 .stream()
                                 .map(ScheduleResponseDto::toDto)
                                 .toList();
    }

    // 선택 일정 조회
    @Override
    public ScheduleResponseDto findById(Long id) {
        return ScheduleResponseDto.toDto(scheduleRepository.findByIdOrElseThrow(id));
    }

    // 일정 수정
    @Transactional
    @Override
    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) {
        // 존재하는 일정 불러오기
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 수정
        findSchedule.update(requestDto.getTitle(), requestDto.getContent());

        return findById(id);
    }

    // 일정 삭제
    @Override
    public void delete(Long id) {
        // 존재하는 일정 불러오기
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 삭제
        scheduleRepository.delete(findSchedule);
    }
}
