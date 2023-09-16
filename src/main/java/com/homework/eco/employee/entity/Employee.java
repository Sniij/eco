package com.homework.eco.employee.entity;

import com.homework.eco.department.entity.Department;
import com.homework.eco.job.entity.Job;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@Entity(name = "employees")
@NoArgsConstructor
public class Employee{

    @Id
    @Column(name = "employee_id", columnDefinition = "INT(11) UNSIGNED NOT NULL")
    private int id;

    @Column(length = 20, name = "first_name")
    private String firstName;

    @Column(length = 25, name = "last_name", nullable = false)
    private String lastName;

    @Column(length = 25, nullable = false)
    private String email;

    @Column(length = 20, name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date", columnDefinition = "DATE NOT NULL")
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job jobId;

    @Column(columnDefinition = "DECIMAL(8,2) NOT NULL")
    private BigDecimal salary;

    @Column(name = "commission_pct", columnDefinition = "DECIMAL(2,2)")
    private BigDecimal commissionPct;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee managerId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department departmentId;

    @Builder
    public Employee(int id, String firstName, String lastName, String email, String phoneNumber, Date hireDate, Job jobId, BigDecimal salary, BigDecimal commissionPct, Employee managerId, Department departmentId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

}
