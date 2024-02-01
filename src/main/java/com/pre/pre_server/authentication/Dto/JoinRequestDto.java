package com.pre.pre_server.authentication.Dto;

import com.pre.pre_server.entity.Role;
import com.pre.pre_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequestDto {

    private String email;
    private String password;
    private String name;
    private int week;
    private int day;
    private String b_name;
    private List supplements;
    private Role role;


    @Builder
    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .role(Role.ROLE_USER)
                .build();
    }
}
