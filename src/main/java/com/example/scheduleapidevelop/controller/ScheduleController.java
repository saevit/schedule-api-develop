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

    /**
     * 일정을 저장
     *
     * @param requestDto 일정의 제목, 내용
     * @param httpRequest 세션 (로그인 된 사용자의 id 필요)
     * @return 생성된 일정
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto requestDto,
                                                    HttpServletRequest httpRequest) {

        // 세션으로부터 유저 id 받아오기
        HttpSession session = httpRequest.getSession(false);
        Object sessionAttribute = session.getAttribute(Const.LOGIN_USER);
        Long sessionUserId = ((UserResponseDto) sessionAttribute).getId();

        // 일정 생성 및 반환
        return new ResponseEntity<>(scheduleService.save(sessionUserId, requestDto), HttpStatus.CREATED);
    }

    /**
     * 전체 일정 조회
     *
     * @return 전체 일정 리스트
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {

        // 일정 조회 및 반환
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    /**
     * 선택 일정 조회
     *
     * @param id 경로로부터 조회할 일정의 id
     * @return 조회된 일정
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {

        // 일정 조회 및 반환
        return new ResponseEntity<>(scheduleService.findById(id), HttpStatus.OK);
    }

    /**
     * 선택한 일정을 수정
     *
     * @param id 경로로부터 수정할 일정의 id
     * @param requestDto 수정할 일정의 제목, 내용
     * @return 수정 수 일정
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody ScheduleRequestDto requestDto) {
        // 일정 수정 및 반환
        return new ResponseEntity<>(scheduleService.update(id, requestDto), HttpStatus.OK);
    }

    /**
     * 일정 삭제
     *
     * @param id 경로로부터 삭제할 일정의 id
     * @return -
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        // id 기반으로 삭제
        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
