package com.pre.pre_server.repository;

import com.pre.pre_server.entity.DietRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietRecordRepository extends JpaRepository<DietRecord, Long> {
    Optional<DietRecord> findById(int id);
}
