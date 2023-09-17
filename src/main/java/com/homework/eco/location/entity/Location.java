package com.homework.eco.location.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", columnDefinition = "INT(11) UNSIGNED NOT NULL")
    private int id;

    @Column(name = "street_address", length = 40)
    private String streetAddress;

    @Column(name = "postal_code", length = 12)
    private String postalCode;

    @Column(name = "city", length = 30, nullable = false)
    private String city;

    @Column(name = "state_province", length =25)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country countryId;

    @Builder
    public Location(int id, String streetAddress, String postalCode, String city, String stateProvince, Country countryId) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.countryId = countryId;
    }

}
