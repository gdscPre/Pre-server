package com.pre.pre_server.supplements.Dto;

import com.pre.pre_server.entity.Supplement;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SuppleCheckDto {
    private Long supplement_id;
    private LocalDateTime date;
    private boolean is_checked;

    @Builder
    public SuppleCheckDto (Long supplement_id, LocalDateTime date, boolean is_checked) {
        this.supplement_id = supplement_id;
        this.date = date;
        this.is_checked = is_checked;
    }

}
