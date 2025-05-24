package com.example.scheduleapidevelop.repository;

import com.example.scheduleapidevelop.common.Exception.NotFoundIdException;
import com.example.scheduleapidevelop.model.entity.Schedule;
import com.example.scheduleapidevelop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundIdException("일정", id));
    }

    void deleteByUser(User findUser);
}
