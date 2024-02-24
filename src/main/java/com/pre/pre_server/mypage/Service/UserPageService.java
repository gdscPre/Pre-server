package com.pre.pre_server.mypage.Service;

import com.pre.pre_server.entity.Supplement;
import com.pre.pre_server.mypage.Dto.ChangeInfoRequestDto;
import com.pre.pre_server.mypage.Dto.UserPageDto;
import com.pre.pre_server.entity.User;
import com.pre.pre_server.repository.SupplementRepository;
import com.pre.pre_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPageService {
    private final UserRepository userRepository;
    private final SupplementRepository supplementRepository;

    // user정보 가져오기
    @Transactional(readOnly = true)
    public UserPageDto getMyInfo(User user) {

        List<Supplement> supplements = supplementRepository.findByUserId(user.getId());
        List<Long> supplementList = supplements.stream()
                .map(Supplement::getId)
                .collect(Collectors.toList());


        UserPageDto userPageDto = UserPageDto.builder()
                .b_name(user.getB_name())
                .user_name(user.getName())
                .week(user.getWeek())
                .day(user.getDay())
                .supplements(supplementList)
                .build();
        return userPageDto;
    }

    // user정보 수정
    @Transactional
    public ResponseEntity changeInfo(User user, ChangeInfoRequestDto requestDto){
        user.updateInfo(requestDto);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}
