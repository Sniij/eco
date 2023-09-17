package com.homework.eco.api.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

public interface APIService {

    String getURLParkingAPI(Integer pIndex, Integer pSize) throws UnsupportedEncodingException;

    String getURLParkingAPI(Integer pIndex, Integer pSize, String address) throws UnsupportedEncodingException;

    JSONObject getJsonObjectByRequestURI(URL requestURI) throws URISyntaxException, ParseException;

    String getJSONStringByJsonArray(JSONArray jsonArray);



}
