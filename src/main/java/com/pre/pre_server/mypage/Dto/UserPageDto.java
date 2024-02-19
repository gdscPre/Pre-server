package com.pre.pre_server.mypage.Dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserPageDto {
    private String b_name;
    private String user_name;
    private int week;
    private int day;
    private List<Long> supplements;

}
