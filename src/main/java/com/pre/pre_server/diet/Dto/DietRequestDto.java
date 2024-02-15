package com.pre.pre_server.diet.Dto;

import com.pre.pre_server.entity.DietRecord;
import com.pre.pre_server.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
public class DietRequestDto {
    private String name; //식품이름
    private float calories; //열량
    private float carbs; //탄수화물
    private float protein; //단백질

    @Builder
    public DietRequestDto(String name, float calories, float carbs, float protein) {
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
    }

    public DietRecord toEntity(User user) {
        return DietRecord.builder()
                .food_name(name)
                .calories(calories)
                .carbs(carbs)
                .protein(protein)
                .time(LocalDateTime.now())
                .user(user)
                .build();
    }

}
