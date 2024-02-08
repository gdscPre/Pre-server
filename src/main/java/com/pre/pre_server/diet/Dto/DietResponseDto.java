package com.pre.pre_server.diet.Dto;

import lombok.Data;

@Data
public class DietResponseDto {
    private String DESC_KOR; //식품이름
    private float NUTR_CONT1; //열량
    private float NUTR_CONT2; //탄수화물
    private float NUTR_CONT3; //단백질

    public DietResponseDto(String DESC_KOR, float NUTR_CONT1, float NUTR_CONT2, float NUTR_CONT3){
        this.DESC_KOR = DESC_KOR;
        this.NUTR_CONT1 = NUTR_CONT1;
        this.NUTR_CONT2 = NUTR_CONT2;
        this.NUTR_CONT3 = NUTR_CONT3;
    }

}
