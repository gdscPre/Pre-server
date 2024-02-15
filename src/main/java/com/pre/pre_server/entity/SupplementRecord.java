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

    private LocalDateTime time;

    private boolean is_checked;

    //외래키
    @ManyToOne
    @JoinColumn(name = "supplement_id")
    private Supplement supplement;

    @Builder
    public SupplementRecord(LocalDateTime time, boolean is_checked, Supplement supplement) {
        this.time = time;
        this.is_checked = is_checked;
        this.supplement = supplement;
    }

    public void change_isChecked(boolean is_checked) {
        this.is_checked = is_checked;
    }
}
