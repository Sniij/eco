package com.homework.eco.location.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id", columnDefinition = "INT(11) UNSIGNED NOT NULL")
    private int id;

    @Column(name = "region_name", length = 25)
    private String regionName;

}
