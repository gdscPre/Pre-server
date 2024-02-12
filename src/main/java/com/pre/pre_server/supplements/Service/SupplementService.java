package com.pre.pre_server.supplements.Service;

import com.pre.pre_server.entity.Supplement;
import com.pre.pre_server.entity.SupplementRecord;
import com.pre.pre_server.entity.User;
import com.pre.pre_server.repository.SuppleRecordRepository;
import com.pre.pre_server.repository.SupplementRepository;
import com.pre.pre_server.supplements.Dto.SuppleCheckDto;
import com.pre.pre_server.supplements.Dto.SupplementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class SupplementService {

    @Autowired
    private final SuppleRecordRepository suppleRecordRepository;

    @Autowired
    private final SupplementRepository supplementRepository;

    private LocalDateTime start = LocalDateTime.now().with(LocalTime.MIN);
    private LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

    //영양제 체크하기
    public void supplementCheck(SuppleCheckDto suppleCheckDto) {

        Supplement supplement = suppleCheckDto.getSupplement();
        LocalDateTime date = suppleCheckDto.getDate();
        boolean isChecked = suppleCheckDto.is_checked();

        // 해당 supplement에 대한 기록 가져오기
        SupplementRecord supplementRecord = suppleRecordRepository.findBySupplementAndTime(supplement, date);

        // is_checked 업데이트
        supplementRecord.change_isChecked(!isChecked);

        // 저장
        suppleRecordRepository.save(supplementRecord);

    }

    //매일 영양제 기록 생기게 체크하기
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void createSupplementRecords() {
        // 모든 영양제 목록 가져오기
        List<Supplement> supplements = supplementRepository.findAll();

        for (Supplement supplement : supplements) {

            SupplementRecord newRecord = SupplementRecord.builder()
                    .time(LocalDateTime.now())
                    .is_checked(false)
                    .supplement(supplement)
                    .build();
            suppleRecordRepository.save(newRecord);

        }
    }

    //영양제 기록 확인하기
    public List<SupplementDto> supplementRecord(User user) {

        // 오늘 날짜인 데이터 목록 찾아서 저장
        List<SupplementRecord> supplementRecords = suppleRecordRepository.findByTimeBetween(start, end);

        // 사용자의 데이터 목록만 필터링
        List<SupplementRecord> resultSupplements = supplementRecords.stream()
                .filter(record -> record.getSupplement().getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());


        // dto에 담기
        List<SupplementDto> supplement_list = resultSupplements.stream()
                .map(record -> SupplementDto.builder()
                        .supplement_id(record.getSupplement().getId())
                        .is_checked(record.is_checked())
                        .build())
                .collect(Collectors.toList());

        return supplement_list;
    }

}
