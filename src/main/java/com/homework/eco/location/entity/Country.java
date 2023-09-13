package com.homework.eco.location.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "countries")
public class Country {

    @Id
    @Column(name = "country_id", columnDefinition = "CHAR(2) NOT NULL")
    private String id;

    @Column(name = "country_name", length =40)
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region regionId;

}
