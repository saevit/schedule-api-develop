package com.example.scheduleapidevelop.controller;

import com.example.scheduleapidevelop.model.dto.*;
import com.example.scheduleapidevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 등록
    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserSignUpRequestDto requestDto) {

        return new ResponseEntity<>(userService.signUp(requestDto), HttpStatus.CREATED);
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
                                       @RequestBody UserDeleteRequestDto requestDto) {

        userService.delete(id, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
