package com.homework.eco.api.restpark.controller;

import com.homework.eco.api.restpark.dto.RestPartDto;
import com.homework.eco.api.restpark.service.RestParkService;
import com.homework.eco.dto.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RestParkController {

    private final RestParkService restParkService;

    public RestParkController(RestParkService restParkService) {
        this.restParkService = restParkService;
    }

    @GetMapping("/parking")
    public ResponseEntity getParkingInfo(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String address
    ) {

        Page<RestPartDto.Parking.Body> responsePage = restParkService.getParkingInfo(page, size, address);

        if(responsePage.isEmpty())
            return new ResponseEntity<>(new MultiResponseDto<>(), HttpStatus.OK);

        List<RestPartDto.Parking.Body> responseList = responsePage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(responseList, responsePage), HttpStatus.OK);
    }


}
