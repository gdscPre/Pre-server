package com.pre.pre_server.supplements.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// 영양제 기록 확인할 때 사용
@Data
public class SupplementDto {
    private Long supplement_id;
    private String supplement_name;
    private boolean is_checked;

    @Builder
    public SupplementDto(Long supplement_id, String supplement_name, boolean is_checked) {
        this.supplement_id = supplement_id;
        this.supplement_name = supplement_name;
        this.is_checked = is_checked;
    }
}
