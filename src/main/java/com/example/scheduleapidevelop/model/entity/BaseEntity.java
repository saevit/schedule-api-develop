package com.example.scheduleapidevelop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 곡용 필드
 * Schedule과 User에 상속
 *
 * 작성일(자동 생성), 수정일(자동 생성 및 자동 업데이트)
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 생성+수정 시간 자동 기록을 위해
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
