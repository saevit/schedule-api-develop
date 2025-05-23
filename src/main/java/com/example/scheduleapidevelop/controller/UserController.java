package com.example.scheduleapidevelop.controller;

import com.example.scheduleapidevelop.common.Const;
import com.example.scheduleapidevelop.model.dto.*;
import com.example.scheduleapidevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 등록 (회원가입)
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserSignUpRequestDto requestDto) {

        return new ResponseEntity<>(userService.signUp(requestDto), HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginRequestDto requestDto,
                                                 HttpServletRequest httpRequest) {

        // 로그인
        UserResponseDto loginUser = userService.login(requestDto);

        // 로그인 성공시 로직
        // Session 반환 및 생성
        HttpSession session = httpRequest.getSession();

        // Session에 로그인 회원 정보를 저장
        session.setAttribute(Const.LOGIN_USER, loginUser);

        // 로그인 성공시 리다이렉트
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest) {

        HttpSession session = httpRequest.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    // 유저 수정 (비번 수정)
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody UserUpdatePasswordRequestDto requestDto) {

        userService.update(id, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @RequestBody UserDeleteRequestDto requestDto,
                                       HttpServletRequest httpRequest) {

        userService.delete(id, requestDto);

        // 로그아웃 처리
        HttpSession session = httpRequest.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
