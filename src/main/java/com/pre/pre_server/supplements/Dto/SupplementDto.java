package com.pre.pre_server.supplements.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// 영양제 기록 확인할 때 사용
@Data
public class SupplementDto {
    private Long supplement_id;
    private boolean is_checked;

    @Builder
    public SupplementDto(Long supplement_id, LocalDateTime date, boolean is_checked) {
        this.supplement_id = supplement_id;
        this.is_checked = is_checked;
    }
}
