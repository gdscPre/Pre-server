package com.pre.pre_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DietRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id")
    private Long id;

    private String food_name;

    private LocalDateTime time;

    private float calories;

    private float carbs;

    private float protein;

    //외래키
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public DietRecord(String food_name, LocalDateTime time, float calories, float carbs, float protein, User user) {
        this.food_name = food_name;
        this.time = time;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.user = user;
    }

}
