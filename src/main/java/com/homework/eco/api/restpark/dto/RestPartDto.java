package com.homework.eco.api.restpark.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONObject;

public class RestPartDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Parking{

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Header{
            private Long totalCount;
            private String code;
            private String massage;

            public Header(JSONObject jsonObject) {
                JSONObject jsonObject1 = (JSONObject) jsonObject.get("header");

                Long count = (Long) jsonObject1.get("totalCount");
                String code = (String) jsonObject1.get("code");
                String msg = (String) jsonObject1.get("massage");

                this.totalCount = count;
                this.code = code;
                this.massage = msg;
            }

            @Builder
            public Header(Long totalCount, String code, String massage) {
                this.totalCount = totalCount;
                this.code = code;
                this.massage = massage;
            }
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class Body{
            private String parkingName;
            private String parkingDiv;
            private String parkingType;
            private String addressRoad;
            private String addressLot;
            private Long availability;
            private String charge;

            private String FIR_THURSDAY;
            private String SEC_FRIDAY;
            private String TRD_SATURDAY;
            private String FOUR_SUNDAY;
            private String FIF_MONDAY;
            private String SIX_TUESDAY;

            public Body(JSONObject jsonObject) {
                this.parkingName = (String) jsonObject.get("TMP01");
                this.parkingDiv = (String)jsonObject.get("PARKPLC_DIV_NM");
                this.parkingType = (String)jsonObject.get("PARKPLC_TYPE");
                this.addressRoad = (String)jsonObject.get("REFINE_ROADNM_ADDR");
                this.addressLot = (String)jsonObject.get("REFINE_LOTNO_ADDR");
                this.availability = (Long)jsonObject.get("PARKNG_COMPRT_PLANE_CNT");
                this.charge = (String)jsonObject.get("CHRG_INFO");
            }

            @Builder
            public Body(String parkingName, String parkingDiv, String parkingType, String addressRoad, String addressLot, Long availability, String charge, String FIR_THURSDAY, String SEC_FRIDAY, String TRD_SATURDAY, String FOUR_SUNDAY, String FIF_MONDAY, String SIX_TUESDAY) {
                this.parkingName = parkingName;
                this.parkingDiv = parkingDiv;
                this.parkingType = parkingType;
                this.addressRoad = addressRoad;
                this.addressLot = addressLot;
                this.availability = availability;
                this.charge = charge;
                this.FIR_THURSDAY = FIR_THURSDAY;
                this.SEC_FRIDAY = SEC_FRIDAY;
                this.TRD_SATURDAY = TRD_SATURDAY;
                this.FOUR_SUNDAY = FOUR_SUNDAY;
                this.FIF_MONDAY = FIF_MONDAY;
                this.SIX_TUESDAY = SIX_TUESDAY;
            }
        }
    }

}
