package com.pre.pre_server.main.Service;

import com.pre.pre_server.entity.User;
import com.pre.pre_server.main.Dto.MainInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MainService {

    public MainInfoDto getUserInfo(User user) {

        MainInfoDto mainInfoDto = MainInfoDto.builder()
                .d_day(user.getD_day())
                .week(user.getWeek())
                .day(user.getDay())
                .b_name(user.getB_name())
                .build();

        return mainInfoDto;

    }

}
