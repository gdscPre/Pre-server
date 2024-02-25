package com.pre.pre_server.repository;

import com.pre.pre_server.entity.Supplement;
import com.pre.pre_server.entity.SupplementRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SuppleRecordRepository  extends JpaRepository<SupplementRecord, Long> {
    SupplementRecord findBySupplementIdAndTimeBetween(Long supplement_id, LocalDateTime start, LocalDateTime end);

    @Query("SELECT sr FROM SupplementRecord sr WHERE DATE(sr.time) = CURDATE() AND sr.supplement.id = :supplementId")
    SupplementRecord findBySupplementIdAndTime(@Param("supplementId") Long supplementId);

    List<SupplementRecord> findByTimeBetween(LocalDateTime start, LocalDateTime end);

}
