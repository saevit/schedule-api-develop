package com.example.scheduleapidevelop.service;

import com.example.scheduleapidevelop.model.dto.UserDeleteRequestDto;
import com.example.scheduleapidevelop.model.dto.UserSignUpRequestDto;
import com.example.scheduleapidevelop.model.dto.UserUpdatePasswordRequestDto;
import com.example.scheduleapidevelop.model.dto.UserResponseDto;
import com.example.scheduleapidevelop.model.entity.User;
import com.example.scheduleapidevelop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    // 유저 등록
    @Override
    public UserResponseDto signUp(UserSignUpRequestDto requestDto) {
        User user = new User(requestDto.getName(),requestDto.getEmail(), requestDto.getPassword());

        return UserResponseDto.toDto(userRepository.save(user));
    }

    // 유저 조회
    @Override
    public UserResponseDto findById(Long id) {

        return UserResponseDto.toDto(userRepository.findByIdOrElseThrow(id));
    }

    // 유저 수정 (비번 수정)
    @Transactional
    @Override
    public void update(Long id, UserUpdatePasswordRequestDto requestDto) {

        User findUser = findAndValiatePassword(id, requestDto.getOldPassword());

        findUser.updatePassword(requestDto.getNewPassword());
    }

    // 유저 삭제
    @Override
    public void delete(Long id, UserDeleteRequestDto requestDto) {

        User findUser = findAndValiatePassword(id, requestDto.getPassword());

        userRepository.delete(findUser);
    }

    // 유저 조회 + 비밀번호 검증
    private User findAndValiatePassword(Long id, String password) {
        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!findUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return findUser;
    }
}
