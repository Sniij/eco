package com.homework.eco.location.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LocationDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Detail{

        private String departmentName;
        private int locationId;
        private String streetAddress;
        private String postalCode;
        private String city;
        private String stateProvince;
        private String countryId;
    }
}
