package com.pre.pre_server.entity;


import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Builder;

import java.util.List;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private int week;
    private int day;
    private String b_name;
    private List supplements;
    private int d_day;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    private User(String email, String password, String name, int week, int day, String b_name, List supplements, int d_day, Role role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.week = week;
        this.day = day;
        this.b_name = b_name;
        this.supplements = supplements;
        this.d_day = d_day;
        this.role = role;
    }
}
