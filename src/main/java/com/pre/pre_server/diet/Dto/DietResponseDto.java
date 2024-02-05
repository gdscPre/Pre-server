package com.pre.pre_server.diet.Dto;

import lombok.Data;

@Data
public class DietResponseDto {
    private String DESC_KOR; //식품이름
    /*
    private float NUTR_CONT1; //열량
    private float NUTR_CONT2; //탄수화물
    private float NUTR_CONT3; //단백질
    private float NUTR_CONT4; //지방
    private float NUTR_CONT5; //당류
     */

    //public DietResponseDto(String DESC_KOR, float NUTR_CONT1, float NUTR_CONT2, float NUTR_CONT3, float NUTR_CONT4, float NUTR_CONT5){
    public DietResponseDto(String DESC_KOR) {
        this.DESC_KOR = DESC_KOR;
        /*
        this.NUTR_CONT1 = NUTR_CONT1;
        this.NUTR_CONT2 = NUTR_CONT2;
        this.NUTR_CONT3 = NUTR_CONT3;
        this.NUTR_CONT4 = NUTR_CONT4;
        this.NUTR_CONT5 = NUTR_CONT5;
         */

    }

}
