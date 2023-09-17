package com.homework.eco.employee.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

public class EmployeeDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Detail{

        private int employeeId;
        private String firstName;
        private String lastName;
        private String jobId;
        private BigDecimal salary;
        private BigDecimal commissionPct;
        private int managerId;
        private int departmentId;

        private String jobTitle;
        private String departmentName;
        private int locationId;
        private String city;
        private String stateProvince;
        private String countryId;
        private String countryName;
        private String regionName;

        @Builder
        public Detail(int employeeId, String firstName, String lastName, String jobId, BigDecimal salary, BigDecimal commissionPct, int managerId, int departmentId, String jobTitle, String departmentName, int locationId, String city, String stateProvince, String countryId, String countryName, String regionName) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.jobId = jobId;
            this.salary = salary;
            this.commissionPct = commissionPct;
            this.managerId = managerId;
            this.departmentId = departmentId;
            this.jobTitle = jobTitle;
            this.departmentName = departmentName;
            this.locationId = locationId;
            this.city = city;
            this.stateProvince = stateProvince;
            this.countryId = countryId;
            this.countryName = countryName;
            this.regionName = regionName;
        }
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch{

        private int employeeId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private Date hireDate;
        private String jobId;
        private BigDecimal salary;
        private BigDecimal commissionPct;
        private int managerId;
        private int departmentId;

        @Builder
        public Patch(int employeeId, String firstName, String lastName, String email, String phoneNumber, String jobId, BigDecimal salary, BigDecimal commissionPct, int managerId, int departmentId, Date hireDate) {
            this.employeeId = employeeId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.jobId = jobId;
            this.salary = salary;
            this.commissionPct = commissionPct;
            this.managerId = managerId;
            this.departmentId = departmentId;
            this.hireDate = hireDate;
        }
    }
}
