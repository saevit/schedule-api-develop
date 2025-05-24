package com.example.scheduleapidevelop.repository;

import com.example.scheduleapidevelop.common.Exception.NotFoundIdException;
import com.example.scheduleapidevelop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundIdException("유저", id));
    }

    Optional<User> findByEmailAndPassword(String email, String password);
}
