package com.homework.eco.location.dto;

import lombok.Builder;
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

        @Builder
        public Detail(String departmentName, int locationId, String streetAddress, String postalCode, String city, String stateProvince, String countryId) {
            this.departmentName = departmentName;
            this.locationId = locationId;
            this.streetAddress = streetAddress;
            this.postalCode = postalCode;
            this.city = city;
            this.stateProvince = stateProvince;
            this.countryId = countryId;
        }
    }
}
