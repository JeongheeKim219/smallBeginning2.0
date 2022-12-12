package com.project.smallbeginjava11.repository;

import com.project.smallbeginjava11.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByMemberMemberIdAndPlannedToBetweenOrderByCreatedAtDesc(Long memberId, LocalDateTime from, LocalDateTime start);
    List<Todo> findByMemberMemberIdAndPlannedToOrderByCreatedAtDesc(Long valueOf, LocalDateTime selectedDate);
}
