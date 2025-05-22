package com.example.scheduleapidevelop.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 일정 테이블
 * 필드     : 작성 유저명, 할일 제목, 할일 내용
 * 상속 필드 : 작성일, 수정일
 */
@Getter
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String content;

    public Schedule() {};

    public Schedule(User user, String title, String content){
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
