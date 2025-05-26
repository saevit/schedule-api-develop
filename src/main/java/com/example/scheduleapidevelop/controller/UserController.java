package com.example.scheduleapidevelop.controller;

import com.example.scheduleapidevelop.common.Const;
import com.example.scheduleapidevelop.model.dto.*;
import com.example.scheduleapidevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     *
     * @param requestDto 가입할 유저의 이름, 이메일, 비밀번호
     * @return 생성된 유저
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserSignUpRequestDto requestDto) {

        // 회원가입 및 반환
        return new ResponseEntity<>(userService.signUp(requestDto), HttpStatus.CREATED);
    }

    /**
     * 로그인
     *
     * @param requestDto 로그인을 위한 이메일, 패스워드
     * @param httpRequest 세션 (로그인 성공 시 회원정보 저장을 위해 필요)
     * @return 로그인 정보와 일치하는 유저 조회
     */
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody UserLoginRequestDto requestDto,
                                                 HttpServletRequest httpRequest) {

        // 로그인
        UserResponseDto loginUser = userService.login(requestDto);

        // 로그인 성공시 로직
        // Session 반환 및 생성
        HttpSession session = httpRequest.getSession();

        // Session에 로그인 회원 정보를 저장
        session.setAttribute(Const.LOGIN_USER, loginUser);

        // 로그인 성공 시 유저값 반환
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }


    /**
     * 로그아웃
     *
     * @param httpRequest 세션 (로그아웃 시 세션 삭제를 위해 필요)
     * @return -
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpRequest) {

        // 기존 세션 불러오기
        HttpSession session = httpRequest.getSession(false);

        // 세션 삭제
        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 유저 조회
     *
     * @param id 경로로부터 조회할 유저의 id
     * @return 조회된 유저
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        // 유저 조회 및 반환
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    /**
     * 비밀번호 수정
     *
     * @param id 경로로부터 비밀번호를 수정할 유저의 id
     * @param requestDto 유저의 현재 비밀번호, 새로운 비밀번호
     * @return -
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @Valid @RequestBody UserUpdatePasswordRequestDto requestDto) {

        // 유저 수정
        userService.update(id, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 유저 삭제
     *
     * @param id 경로로부터 삭제할 유저의 id
     * @param requestDto 삭제할 유저의 비밀번호
     * @param httpRequest 세션 (유저 삭제 시 세션 삭제를 위해 필요)
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @Valid @RequestBody UserDeleteRequestDto requestDto,
                                       HttpServletRequest httpRequest) {

        // 유저 삭제
        userService.delete(id, requestDto);

        // 로그아웃 처리 (세션 삭제)
        HttpSession session = httpRequest.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
