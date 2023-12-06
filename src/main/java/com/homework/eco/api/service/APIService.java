package com.homework.eco.api.service;

import java.io.UnsupportedEncodingException;

public interface APIService {

    String getURLParkingAPI(Integer pIndex, Integer pSize) throws UnsupportedEncodingException;

    String getURLParkingAPI(Integer pIndex, Integer pSize, String address) throws UnsupportedEncodingException;


}
