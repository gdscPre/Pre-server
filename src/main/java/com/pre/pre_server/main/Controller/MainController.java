package com.pre.pre_server.main.Controller;

import com.pre.pre_server.entity.User;
import com.pre.pre_server.main.Dto.MainInfoDto;
import com.pre.pre_server.main.Service.MainService;
import com.pre.pre_server.supplements.Dto.SuppleCheckDto;
import com.pre.pre_server.supplements.Service.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
public class MainController {
    private final SupplementService supplementService;
    private final MainService mainService;

    @Autowired
    public MainController(SupplementService supplementService, MainService mainService){
        this.supplementService = supplementService;
        this.mainService = mainService;
    }

    // 메인화면
    @GetMapping()
    public MainInfoDto mainInfo(@AuthenticationPrincipal User user) {

        return mainService.getUserInfo(user);
    }

    // 영양제 체크하기
    @PostMapping("/check")
    public void supplementCheck(@RequestBody SuppleCheckDto suppleCheckDto) {

        supplementService.supplementCheck(suppleCheckDto);
    }


}
