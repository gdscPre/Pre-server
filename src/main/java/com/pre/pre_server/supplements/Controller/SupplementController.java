package com.pre.pre_server.supplements.Controller;

import com.pre.pre_server.entity.User;
import com.pre.pre_server.supplements.Dto.SupplementDto;
import com.pre.pre_server.supplements.Service.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/supplement")
public class SupplementController {

    private final SupplementService supplementService;

    @Autowired
    public SupplementController(SupplementService supplementService){
        this.supplementService = supplementService;
    }

    //영양제 기록 확인하기
    @GetMapping()
    public List<SupplementDto> supplementRecord(@AuthenticationPrincipal User user) {

        return supplementService.supplementRecord(user);
    }

}
