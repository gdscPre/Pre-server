package com.pre.pre_server.repository;

import com.pre.pre_server.entity.DietRecord;
import com.pre.pre_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DietRecordRepository extends JpaRepository<DietRecord, Long> {
    Optional<DietRecord> findById(int id);
    List<DietRecord> findByUserAndTimeBetween(User user, LocalDateTime start,LocalDateTime end);

}
