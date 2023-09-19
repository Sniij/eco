package com.homework.eco.api.controller;

import com.homework.eco.api.dto.APIDto;
import com.homework.eco.api.restpark.service.RestParkService;
import com.homework.eco.api.service.APIServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@RestController
public class APIController {

    private final APIServiceImpl apiService;
    private final RestParkService restParkService;

    public APIController(APIServiceImpl apiService, RestParkService restParkService) {
        this.apiService = apiService;
        this.restParkService = restParkService;
    }

    @GetMapping("/parking")
    public ResponseEntity getParkingInfo(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam String address
    ) throws Exception {

        URL requestURL = new URL(apiService.getURLParkingAPI(page, size,address));

        JSONObject httpResponse = apiService.getJsonObjectByRequestURI(requestURL);

        if(httpResponse.containsKey("RESULT")){

            return ResponseEntity.ok(httpResponse);
        }

        JSONArray head = apiService.getParkingHeadJSONArray(httpResponse);
        JSONObject jsonHead = apiService.getParkingHeadObject(head);

        APIDto.Parking.Header header = new APIDto.Parking.Header(jsonHead);

        JSONArray row = apiService.getParkingRowJSONArray(httpResponse);
        List<APIDto.Parking.Body> body = apiService.makeParkingBodyByJSONArray(row);

        JSONObject response = new JSONObject();
        response.put("header", header);
        response.put("body", body);



        return ResponseEntity.ok(response);
    }


}
