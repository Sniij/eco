package com.homework.eco.api.restpark.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "rest_parks")
public class RestPark {

    @Id
    private String REFINE_LOTNO_ADDR;
    private String FIR_THURSDAY;
    private String SEC_FRIDAY;
    private String TRD_SATURDAY;
    private String FOUR_SUNDAY;
    private String FIF_MONDAY;
    private String SIX_TUESDAY;

    @Builder
    public RestPark(String REFINE_LOTNO_ADDR, String FIR_THURSDAY, String SEC_FRIDAY, String TRD_SATURDAY, String FOUR_SUNDAY, String FIF_MONDAY, String SIX_TUESDAY) {
        this.REFINE_LOTNO_ADDR = REFINE_LOTNO_ADDR;
        this.FIR_THURSDAY = FIR_THURSDAY;
        this.SEC_FRIDAY = SEC_FRIDAY;
        this.TRD_SATURDAY = TRD_SATURDAY;
        this.FOUR_SUNDAY = FOUR_SUNDAY;
        this.FIF_MONDAY = FIF_MONDAY;
        this.SIX_TUESDAY = SIX_TUESDAY;
    }
}
