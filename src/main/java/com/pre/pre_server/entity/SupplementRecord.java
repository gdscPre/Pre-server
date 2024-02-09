package com.pre.pre_server.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class SupplementRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    private LocalDateTime supple_time;

    private boolean is_checked;

    //외래키
    @ManyToOne
    @JoinColumn(name = "supplement_id")
    private Supplement supplement;

    @Builder
    public SupplementRecord(LocalDateTime supple_time, boolean is_checked, Supplement supplement) {
        this.supple_time = supple_time;
        this.is_checked = is_checked;
        this.supplement = supplement;
    }
}
