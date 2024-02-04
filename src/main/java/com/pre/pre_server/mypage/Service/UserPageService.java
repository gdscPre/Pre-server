package com.pre.pre_server.mypage.Service;

import com.pre.pre_server.mypage.Dto.ChangeInfoRequestDto;
import com.pre.pre_server.mypage.Dto.UserPageDto;
import com.pre.pre_server.entity.User;
import com.pre.pre_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPageService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserPageDto getMyInfo(User user){
        UserPageDto userPageDto = UserPageDto.builder()
                .b_name(user.getB_name())
                .week(user.getWeek())
                .day(user.getDay())
                .build();
        return userPageDto;
    }

    @Transactional
    public ResponseEntity changeInfo(User user, ChangeInfoRequestDto requestDto){
        user.updateInfo(requestDto);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}
