package com.homework.eco.api.restpark.entity;

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
}
