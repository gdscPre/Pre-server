package com.pre.pre_server.mypage.Controller;

import com.pre.pre_server.mypage.Dto.ChangeInfoRequestDto;
import com.pre.pre_server.mypage.Dto.UserPageDto;
import com.pre.pre_server.mypage.Service.UserPageService;
import com.pre.pre_server.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final UserPageService userPageService;
    //마이페이지 조회
    @GetMapping()
    public UserPageDto getUserInfo(@AuthenticationPrincipal User user) {
        return userPageService.getMyInfo(user);
    }

    //사용자 정보 수정
    @PatchMapping("/modify")
    public ResponseEntity modifyProfile(@AuthenticationPrincipal User user, @RequestBody ChangeInfoRequestDto requestDto){
        return userPageService.changeInfo(user, requestDto);
    }
}
