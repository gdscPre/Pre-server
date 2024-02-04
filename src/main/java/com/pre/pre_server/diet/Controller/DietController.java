package com.pre.pre_server.diet.Controller;


import com.pre.pre_server.diet.Dto.DietResponseDto;
import com.pre.pre_server.diet.Service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import org.json.simple.parser.ParseException;

@RestController
@RequestMapping("/diet")
public class DietController {

    private final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping("list")
    public void getFood() throws IOException, ParseException {
        dietService.getFood();
        //return dietService.getFood();
    }
}
