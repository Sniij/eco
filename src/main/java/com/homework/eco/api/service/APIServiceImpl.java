package com.homework.eco.api.service;

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

@Service
public class APIServiceImpl implements APIService{

    @Value("${serviceKey}")
    private String serviceKey;

    private StringBuilder urlBuilder;


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

    public ResponseEntity<String> getResponse(URL requestURL) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForEntity(requestURL.toURI(), String.class);
    }

    public JSONObject convertToJSONObject(String responseBody) throws ParseException {
        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(responseBody);
    }


}
