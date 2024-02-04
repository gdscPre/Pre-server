package com.pre.pre_server.mypage.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPageDto {
    private String b_name;
    private int week;
    private int day;

}
