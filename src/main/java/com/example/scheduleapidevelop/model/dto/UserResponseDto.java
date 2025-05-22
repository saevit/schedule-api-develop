package com.example.scheduleapidevelop.model.dto;

import com.example.scheduleapidevelop.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private final Long id;
    private final String name;
    private final String email;

    public static UserResponseDto toDto(User User) {
        return new UserResponseDto( User.getId(),
                                    User.getName(),
                                    User.getEmail() );
    }
}
