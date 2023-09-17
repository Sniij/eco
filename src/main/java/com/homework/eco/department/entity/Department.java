package com.homework.eco.department.entity;

import com.homework.eco.employee.entity.Employee;
import com.homework.eco.location.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity(name = "departments")
public class Department {

    @Id
    @Column(name = "department_id", columnDefinition = "INT(11) UNSIGNED NOT NULL")
    private int id;

    @Column(name = "department_name", length = 30, nullable = false)
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee managerId;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location locationId;

    @Builder
    public Department(int id, String departmentName, Employee managerId, Location locationId) {
        this.id = id;
        this.departmentName = departmentName;
        this.managerId = managerId;
        this.locationId = locationId;
    }
}
