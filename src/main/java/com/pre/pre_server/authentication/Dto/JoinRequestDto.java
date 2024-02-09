package com.pre.pre_server.authentication.Dto;

import com.pre.pre_server.entity.Role;
import com.pre.pre_server.entity.Supplement;
import com.pre.pre_server.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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
    private List<String> supplements;
    private Role role;


    @Builder
    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .week(week)
                .day(day)
                .b_name(b_name)
                .role(Role.ROLE_USER)
                .build();
        return user;
    }

    public List<Supplement> toSupplementEntities(User user) {
        if (supplements == null || supplements.isEmpty()) {
            return Collections.emptyList();
        }

        return supplements.stream()
                .map(supplementName -> Supplement.builder().name(supplementName).user(user).build())
                .collect(Collectors.toList());
    }

}
