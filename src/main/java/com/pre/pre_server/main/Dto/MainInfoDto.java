package com.pre.pre_server.main.Dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MainInfoDto {

    private int d_day;
    private int week;
    private int day;
    private String b_name;

    @Builder
    public MainInfoDto(int d_day, int week, int day, String b_name) {
        this.d_day = d_day;
        this.week = week;
        this.day = day;
        this.b_name = b_name;
    }

}
