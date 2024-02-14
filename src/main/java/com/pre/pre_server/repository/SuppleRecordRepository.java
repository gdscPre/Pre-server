package com.pre.pre_server.repository;

import com.pre.pre_server.entity.Supplement;
import com.pre.pre_server.entity.SupplementRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SuppleRecordRepository  extends JpaRepository<SupplementRecord, Long> {
    SupplementRecord findBySupplementIdAndTimeBetween(Long supplement_id, LocalDateTime start, LocalDateTime end);

    List<SupplementRecord> findByTimeBetween(LocalDateTime start, LocalDateTime end);

}
