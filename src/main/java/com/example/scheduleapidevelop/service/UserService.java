package com.example.scheduleapidevelop.service;

import com.example.scheduleapidevelop.model.dto.UserDeleteRequestDto;
import com.example.scheduleapidevelop.model.dto.UserSignUpRequestDto;
import com.example.scheduleapidevelop.model.dto.UserUpdatePasswordRequestDto;
import com.example.scheduleapidevelop.model.dto.UserResponseDto;

public interface UserService {

    UserResponseDto signUp(UserSignUpRequestDto requestDto);

    UserResponseDto findById(Long id);

    void update(Long id, UserUpdatePasswordRequestDto requestDto);

    void delete(Long id, UserDeleteRequestDto requestDto);
}
