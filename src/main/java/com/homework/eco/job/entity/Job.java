package com.homework.eco.job.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity(name = "jobs")
public class Job {

    @Id
    @Column(name = "job_id", length = 10, nullable = false)
    private String id;

    @Column(name = "job_title", length = 35, nullable = false)
    private String jobTitle;

    @Column(name = "min_salary", columnDefinition = "DECIMAL(8,0) UNSIGNED")
    private BigDecimal minSalary;

    @Column(name = "max_salary", columnDefinition = "DECIMAL(8,0) UNSIGNED")
    private BigDecimal maxSalary;


}
