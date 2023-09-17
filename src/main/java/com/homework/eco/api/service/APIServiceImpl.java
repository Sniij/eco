package com.homework.eco.api.service;

import com.homework.eco.api.dto.APIDto;
import com.homework.eco.api.restpark.entity.RestPark;
import com.homework.eco.api.restpark.service.RestParkService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class APIServiceImpl implements APIService{

    @Value("${serviceKey}")
    private String serviceKey;

    private StringBuilder urlBuilder;
    private final RestParkService restParkService;

    public APIServiceImpl(RestParkService restParkService) {
        this.restParkService = restParkService;
    }

    @Override
    public String getURLParkingAPI(Integer pIndex, Integer pSize) throws UnsupportedEncodingException {

        urlBuilder = new StringBuilder("https://openapi.gg.go.kr/ParkingPlace");

        urlBuilder.append("?" + URLEncoder.encode("Key", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("Type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pIndex", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pIndex), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pSize", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pSize), "UTF-8"));

        return urlBuilder.toString();
    }
    @Override
    public String getURLParkingAPI(Integer pIndex, Integer pSize, String address) throws UnsupportedEncodingException {

        urlBuilder = new StringBuilder("https://openapi.gg.go.kr/ParkingPlace");

        urlBuilder.append("?" + URLEncoder.encode("Key", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("Type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pIndex", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pIndex), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pSize", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pSize), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("REFINE_LOTNO_ADDR", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8"));

        return urlBuilder.toString();
    }

    @Override
    public JSONObject getJsonObjectByRequestURI(URL requestURI) throws URISyntaxException, ParseException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestURI.toURI(), String.class);

        String responseBody = responseEntity.getBody();
        JSONParser parser = new JSONParser();

        JSONObject jsonObject = (JSONObject) parser.parse(responseBody);

        return jsonObject;
    }

    @Override
    public String getJSONStringByJsonArray(JSONArray jsonArray) {

        return jsonArray.toJSONString();
    }

    public JSONArray getParkingHeadJSONArray(URL requestURL) throws URISyntaxException, ParseException {
        JSONObject jsonObject = getJsonObjectByRequestURI(requestURL);
        JSONArray jsonArray = (JSONArray) jsonObject.get("ParkingPlace");
        JSONObject head = (JSONObject) jsonArray.get(0);
        JSONArray jsonArray2 = (JSONArray) head.get("head");

        return jsonArray2;
    }

    public JSONArray getParkingRowJSONArray(URL requestURL) throws URISyntaxException, ParseException {
        JSONObject jsonObject = getJsonObjectByRequestURI(requestURL);
        JSONArray jsonArray = (JSONArray) jsonObject.get("ParkingPlace");
        JSONObject head = (JSONObject) jsonArray.get(1);
        JSONArray jsonArray2 = (JSONArray) head.get("row");


        return jsonArray2;
    }

    public JSONObject getParkingHeadObject(JSONArray jsonArray) throws URISyntaxException, ParseException {
        Long totalCount = (Long) ((JSONObject) jsonArray.get(0)).get("list_total_count");
        JSONObject result = (JSONObject) jsonArray.get(1);
        String code = (String) ((JSONObject) result.get("RESULT")).get("CODE");
        String massage = (String) ((JSONObject) result.get("RESULT")).get("MESSAGE");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("code", code);
        jsonObject.put("massage", massage);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("header", jsonObject);

        return jsonObject1;
    }

    public List<APIDto.Parking.Body> makeParkingBodyByJSONArray(JSONArray row) {

        List<APIDto.Parking.Body> body = new ArrayList<>();

        for (int i = 0; i < row.size(); i++){

            JSONObject jsonObject = (JSONObject) row.get(i);
            APIDto.Parking.Body bodyDto = new APIDto.Parking.Body(jsonObject);

            String address = bodyDto.getAddressLot().replace("번지","");
            RestPark restPark = null;
            restPark = restParkService.findRestPark(address);
            if(restPark != null){
                bodyDto.setFIR_THURSDAY(restPark.getFIR_THURSDAY());
                bodyDto.setSEC_FRIDAY(restPark.getSEC_FRIDAY());
                bodyDto.setTRD_SATURDAY(restPark.getTRD_SATURDAY());
                bodyDto.setFOUR_SUNDAY(restPark.getFOUR_SUNDAY());
                bodyDto.setFIF_MONDAY(restPark.getFIF_MONDAY());
                bodyDto.setSIX_TUESDAY(restPark.getSIX_TUESDAY());
            }else {
                bodyDto.setFIR_THURSDAY("해당없음(데이터 없음)");
                bodyDto.setSEC_FRIDAY("해당없음(데이터 없음)");
                bodyDto.setTRD_SATURDAY("해당없음(데이터 없음)");
                bodyDto.setFOUR_SUNDAY("해당없음(데이터 없음)");
                bodyDto.setFIF_MONDAY("해당없음(데이터 없음)");
                bodyDto.setSIX_TUESDAY("해당없음(데이터 없음)");
            }
            body.add(bodyDto);
        }
        return body;
    }
}
