package com.example.scheduleapidevelop.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 유저 테이블
 *
 * 필드     : 유저명, 이메일, 비밀번호
 * 상속 필드 : 작성일(자동 생성), 수정일(자동 생성 및 자동 업데이트)
 */
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User() {};

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
