package com.pre.pre_server.authentication.Controller;

import com.pre.pre_server.authentication.Dto.JoinRequestDto;
import com.pre.pre_server.authentication.Jwt.JwtTokenProvider;
import com.pre.pre_server.authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity join(@RequestBody JoinRequestDto requestDto) {
        userService.join(requestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}
