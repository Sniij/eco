package com.homework.eco.api.restpark.service;

import com.homework.eco.api.restpark.dto.RestPartDto;
import com.homework.eco.api.restpark.entity.RestPark;
import com.homework.eco.api.restpark.repository.RestParkRepository;
import com.homework.eco.api.service.APIServiceImpl;
import com.homework.eco.exception.BusinessLogicException;
import com.homework.eco.exception.ExceptionCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestParkService {
    private final RestParkRepository restParkRepository;

    private final APIServiceImpl apiService;

    public RestParkService(RestParkRepository restParkRepository, APIServiceImpl apiService) {
        this.restParkRepository = restParkRepository;
        this.apiService = apiService;
    }

    public RestPark findRestPark(String refine_lotno_addr){
        return findVerifiedRestPark(refine_lotno_addr);
    }

    public RestPark findVerifiedRestPark(String refine_lotno_addr){
        Optional<RestPark> restPark = restParkRepository.findByLotnoAddr(refine_lotno_addr);

        return restPark.orElse( null
        );
    }



    public JSONArray getParkingHeadJSONArray(JSONObject jsonObject) {
        //JSONObject jsonObject = getJsonObjectByRequestURI(requestURL);
        JSONArray jsonArray = (JSONArray) jsonObject.get("ParkingPlace");
        JSONObject head = (JSONObject) jsonArray.get(0);

        return (JSONArray) head.get("head");
    }

    public JSONArray getParkingRowJSONArray(JSONObject jsonObject){
        //JSONObject jsonObject = getJsonObjectByRequestURI(requestURL);
        JSONArray jsonArray = (JSONArray) jsonObject.get("ParkingPlace");
        JSONObject head = (JSONObject) jsonArray.get(1);
        JSONArray jsonArray2 = (JSONArray) head.get("row");


        return jsonArray2;
    }

    public JSONObject getParkingHeadObject(JSONArray jsonArray){
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

    public List<RestPartDto.Parking.Body> makeParkingBodyByJSONArray(JSONArray row) {

        List<RestPartDto.Parking.Body> body = new ArrayList<>();

        for (int i = 0; i < row.size(); i++){

            JSONObject jsonObject = (JSONObject) row.get(i);
            RestPartDto.Parking.Body bodyDto = new RestPartDto.Parking.Body(jsonObject);

            String address = bodyDto.getAddressLot().replace("번지","");
            RestPark restPark = null;
            restPark = findRestPark(address);
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

    public Page<RestPartDto.Parking.Body> getParkingInfo(int page, int size, String address) {

        JSONObject httpResponse;
        List<RestPartDto.Parking.Body> body = new ArrayList<>();
        try {
            URL requestURL = new URL(apiService.getURLParkingAPI(page, size, address));
            String responseBody = apiService.getResponse(requestURL).getBody();
            httpResponse = apiService.convertToJSONObject(responseBody);

            if(httpResponse.containsKey("RESULT")){
                return new PageImpl<>(body);
            }

        } catch (Exception e) {
            throw new BusinessLogicException(ExceptionCode.BAD_REQUEST);
        }

        JSONArray row = getParkingRowJSONArray(httpResponse);
        JSONArray head = getParkingHeadJSONArray(httpResponse);
        Long totalCount = (Long) ((JSONObject) head.get(0)).get("list_total_count");

        body = makeParkingBodyByJSONArray(row);
        Pageable pageable = PageRequest.of(page-1, size);

        return new PageImpl<>(body,pageable,totalCount);
    }
}
