package com.homework.eco.job.entity;

import com.homework.eco.employee.entity.Employee;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
@Getter
public class JobHistoryId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "start_date", columnDefinition = "DATE NOT NULL")
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date startDate;

}
