package com.pre.pre_server.diet.Dto;

import lombok.Data;

import java.util.List;

@Data
public class DietAnalysisDto {
    private List<DietRequestDto> food_list;
    private float sum_calories; //열량 합
    private float sum_carbs; //탄수화물 합
    private float sum_protein; //단백질 합

    public DietAnalysisDto(List<DietRequestDto> food_list, float sum_calories, float sum_carbs, float sum_protein) {
        this.food_list = food_list;
        this.sum_calories = sum_calories;
        this.sum_carbs = sum_carbs;
        this.sum_protein = sum_protein;
    }
}
