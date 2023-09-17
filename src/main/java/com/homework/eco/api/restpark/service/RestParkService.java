package com.homework.eco.api.restpark.service;

import com.homework.eco.api.restpark.entity.RestPark;
import com.homework.eco.api.restpark.repository.RestParkRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestParkService {
    private final RestParkRepository restParkRepository;

    public RestParkService(RestParkRepository restParkRepository) {
        this.restParkRepository = restParkRepository;
    }

    public RestPark findRestPark(String refine_lotno_addr){
        return findVerifiedRestPark(refine_lotno_addr);
    }

    public RestPark findVerifiedRestPark(String refine_lotno_addr){
        Optional<RestPark> restPark = restParkRepository.findByLotnoAddr(refine_lotno_addr);

        return restPark.orElse( null
        );
    }
}
