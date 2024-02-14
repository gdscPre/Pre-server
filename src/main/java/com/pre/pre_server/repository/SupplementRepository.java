package com.pre.pre_server.repository;

import com.pre.pre_server.entity.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SupplementRepository extends JpaRepository<Supplement, Long> {

    List<Supplement> findByUserId(Long user_id);
    List<Supplement> findAll();

}
