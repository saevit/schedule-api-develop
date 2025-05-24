package com.example.scheduleapidevelop.controller;

import com.example.scheduleapidevelop.common.Const;
import com.example.scheduleapidevelop.model.dto.ScheduleRequestDto;
import com.example.scheduleapidevelop.model.dto.ScheduleResponseDto;
import com.example.scheduleapidevelop.model.dto.UserResponseDto;
import com.example.scheduleapidevelop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 저장
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto requestDto,
                                                    HttpServletRequest httpRequest) {

        // 세션으로부터 유저 id 받아오기
        HttpSession session = httpRequest.getSession(false);
        Object sessionAttribute = session.getAttribute(Const.LOGIN_USER);
        Long sessionUserId = ((UserResponseDto) sessionAttribute).getId();

        return new ResponseEntity<>(scheduleService.save(sessionUserId, requestDto), HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {

        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {

        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody ScheduleRequestDto requestDto) {

        return new ResponseEntity<>(scheduleService.update(id, requestDto), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
