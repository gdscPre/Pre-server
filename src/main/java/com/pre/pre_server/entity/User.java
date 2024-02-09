package com.pre.pre_server.entity;


import com.pre.pre_server.mypage.Dto.ChangeInfoRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String refreshToken;
    private String name;
    private int week;
    private int day;
    private String b_name;
    private int d_day;

    @OneToMany(mappedBy = "user")
    private List<Supplement> supplements = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<DietRecord> dietRecords = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public void updateInfo(ChangeInfoRequestDto requestDto){
        this.b_name = requestDto.getB_name();
        this.week = requestDto.getWeek();
        this.day = requestDto.getDay();
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void updateRefreshToken (String refreshToken) {
        this.refreshToken = refreshToken;
    }


    @Builder
    private User(String email, String password, String name, int week, int day, String b_name, int d_day, Role role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.week = week;
        this.day = day;
        this.b_name = b_name;
        this.d_day = d_day;
        this.role = role;
    }


    //UserDetail implement 한 내용
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role.name()));
        return auth;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
