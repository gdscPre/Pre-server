package com.pre.pre_server.diet.Controller;


import com.pre.pre_server.diet.Dto.DietAnalysisDto;
import com.pre.pre_server.diet.Dto.DietRequestDto;
import com.pre.pre_server.diet.Dto.DietResponseDto;
import com.pre.pre_server.diet.Service.DietService;
import com.pre.pre_server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

@RestController
@RequestMapping("/diet")
public class DietController {

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    //식단 목록
    @GetMapping("/list")
    public List<DietResponseDto> getFood() throws IOException, ParseException {
        return dietService.getFood();
    }

    //식단 기록(선택)
    @PostMapping("/select")
    public void selectDiet(@AuthenticationPrincipal User user, @RequestBody DietRequestDto requestDto) {
        dietService.recordDiet(user, requestDto);
    }

    //식단 기록(직접)
    @PostMapping("/record")
    public void recordDiet(@AuthenticationPrincipal User user, @RequestBody DietRequestDto requestDto) {
        dietService.recordDiet(user, requestDto);
    }

    //식단 분석
    @GetMapping("/analysis")
    public DietAnalysisDto getAnalysis(@AuthenticationPrincipal User user) {
        return dietService.analysisDiet(user);
    }


}
