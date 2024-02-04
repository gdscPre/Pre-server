package com.pre.pre_server.authentication.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDto {
    private String grantType;
    private String jwtAccessToken;
    private String jwtRefreshToken;
}
