package com.pre.pre_server.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
public class Supplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplement_id")
    private Long id;

    private String supple_name;

    @OneToMany(mappedBy = "supplement")
    private List<SupplementRecord> supplementRecords = new ArrayList<>();

    //외래키
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Supplement(String supple_name, User user) {
        this.supple_name = supple_name;
        this.user = user;
    }
}
