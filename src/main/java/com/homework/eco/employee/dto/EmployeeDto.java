package com.homework.eco.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    }

}
