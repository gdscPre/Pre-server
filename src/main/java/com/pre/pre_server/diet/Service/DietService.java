package com.pre.pre_server.diet.Service;

import com.pre.pre_server.diet.Dto.DietResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class DietService {

    @Value("${diet_secret}")
    private String secretKey;

    public void getFood() throws IOException, ParseException {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/

        urlBuilder.append("?serviceKey=" + secretKey); //Service Key
        urlBuilder.append("&desc_kor=" + URLEncoder.encode("바나나칩", "UTF-8")); //식품이름
        urlBuilder.append("&pageNo=1"); //페이지번호
        urlBuilder.append("&numOfRows=10"); //한 페이지 결과 수
        urlBuilder.append("&bgn_year=2017"); //구축년도
        urlBuilder.append("&animal_plant=" + URLEncoder.encode("(유)돌코리아", "UTF-8")); //가공업체
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

        /*
        //파싱
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

        JSONObject body = (JSONObject) jsonObject.get("body");
        JSONArray items = (JSONArray) body.get("items");

        JSONObject dataObject = (JSONObject) items.get(0);

        String DESC_KOR = dataObject.get("DESC_KOR").toString();
        int NUTR_CONT1 = Integer.parseInt(dataObject.get("NUTR_CONT1").toString());
        int NUTR_CONT2 = Integer.parseInt(dataObject.get("NUTR_CONT2").toString());
        int NUTR_CONT3 = Integer.parseInt(dataObject.get("NUTR_CONT3").toString());
        int NUTR_CONT4 = Integer.parseInt(dataObject.get("NUTR_CONT4").toString());
        int NUTR_CONT5 = Integer.parseInt(dataObject.get("NUTR_CONT5").toString());


        DietResponseDto response = new DietResponseDto(DESC_KOR, NUTR_CONT1, NUTR_CONT2, NUTR_CONT3, NUTR_CONT4, NUTR_CONT5);

        return response;
         */

    }
}
