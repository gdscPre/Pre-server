package com.pre.pre_server.diet.Service;

import com.pre.pre_server.diet.Dto.*;
import com.pre.pre_server.entity.*;
import com.pre.pre_server.repository.DietRecordRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DietService {

    @Value("${diet.secret}")
    private String secretKey;

    @Autowired
    private final DietRecordRepository dietRecordRepository;

    //음식 데이터 가져오기
    public List<DietResponseDto> getFood() throws IOException, ParseException {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/

        urlBuilder.append("?serviceKey=" + secretKey); //Service Key
        //urlBuilder.append("&desc_kor=" + URLEncoder.encode("바나나칩", "UTF-8")); //식품이름
        urlBuilder.append("&pageNo=2"); //페이지번호
        urlBuilder.append("&numOfRows=100"); //한 페이지 결과 수
        //urlBuilder.append("&bgn_year=2017"); //구축년도
        //urlBuilder.append("&animal_plant=" + URLEncoder.encode("(유)돌코리아", "UTF-8")); //가공업체
        urlBuilder.append("&type=" + URLEncoder.encode("json", "UTF-8")); //응답데이터 형식(xml/json) Default: xml

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        // HTTP 연결 닫기
        conn.disconnect();
        String responseBody = sb.toString();

        System.out.println(sb.toString());

        //파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

        JSONObject body = (JSONObject) jsonObject.get("body");
        JSONArray items = (JSONArray) body.get("items");

        List<DietResponseDto> dietResArr = new ArrayList<>();

        for(int i=0; i<items.size(); i++) {
            JSONObject dataObject = (JSONObject) items.get(i);

            String DESC_KOR = dataObject.get("DESC_KOR").toString();
            float NUTR_CONT1 = parsingValue((String) dataObject.get("NUTR_CONT1"));
            float NUTR_CONT2 = parsingValue((String) dataObject.get("NUTR_CONT2"));
            float NUTR_CONT3 = parsingValue((String) dataObject.get("NUTR_CONT3"));

            DietResponseDto response = new DietResponseDto(DESC_KOR, NUTR_CONT1, NUTR_CONT2, NUTR_CONT3);

            dietResArr.add(response);
        }

        return dietResArr;

    }

    //"N/A"일 경우 0으로 저장
    private static float parsingValue(String value) {
        try {
            return value.equals("N/A") ? 0.0f : Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    //식단 기록
    @Transactional
    public void recordDiet(User user, DietRequestDto dietRequestDto) {
        DietRecord dietRecord = dietRequestDto.toEntity(user);

        dietRecordRepository.save(dietRecord);
    }

    //식단 분석
    @Transactional
    public DietAnalysisDto analysisDiet(User user) {

        // 오늘 날짜인 데이터 목록 찾아서 저장
        LocalDateTime start = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        List<DietRecord> entity_list = dietRecordRepository.findByUserAndTimeBetween(user, start, end);

        List<DietRequestDto> food_list = entity_list.stream()
                .map(record -> DietRequestDto.builder()
                        .name(record.getFood_name())
                        .calories(record.getCalories())
                        .carbs(record.getCarbs())
                        .protein(record.getProtein())
                        .build())
                .collect(Collectors.toList());

        // 찾은 데이터 합하기
        float sum_calories = 0;
        float sum_carbs = 0;
        float sum_protein = 0;

        for(int i=0; i<food_list.size(); i++) {
            sum_calories += food_list.get(i).getCalories();
            sum_carbs += food_list.get(i).getCarbs();
            sum_protein += food_list.get(i).getProtein();
        }

        // dto에 담기
        DietAnalysisDto dietAnalysisDto = new DietAnalysisDto(food_list, sum_calories, sum_carbs, sum_protein);

        return dietAnalysisDto;
    }


}
