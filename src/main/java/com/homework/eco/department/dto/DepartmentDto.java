package com.homework.eco.department.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DepartmentDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Detail{
        private int departmentId;
        private String departmentName;
        private int managerId;
        private int locationId;

        @Builder
        public Detail(int departmentId, String departmentName, int managerId, int locationId) {
            this.departmentId = departmentId;
            this.departmentName = departmentName;
            this.managerId = managerId;
            this.locationId = locationId;
        }
    }
}
