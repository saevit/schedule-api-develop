package com.example.scheduleapidevelop.service;

import com.example.scheduleapidevelop.model.dto.*;

public interface UserService {

    UserResponseDto signUp(UserSignUpRequestDto requestDto);

    UserResponseDto login(UserLoginRequestDto requestDto);

    UserResponseDto findById(Long id);

    void update(Long id, UserUpdatePasswordRequestDto requestDto);

    void delete(Long id, UserDeleteRequestDto requestDto);
}
