package com.pre.pre_server.repository;

import com.pre.pre_server.entity.Supplement;
import com.pre.pre_server.entity.SupplementRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface SuppleRecordRepository  extends JpaRepository<SupplementRecord, Long> {
    SupplementRecord findBySupplementAndSuppleTime(Supplement supplement, LocalDateTime date);

}
