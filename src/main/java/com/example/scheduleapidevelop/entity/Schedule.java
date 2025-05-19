package com.example.scheduleapidevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 일정 테이블
 * 필드     : 작성 유저명, 할일 제목, 할일 내용
 * 상속 필드 : 작성일, 수정일
 */
@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String content;
}
