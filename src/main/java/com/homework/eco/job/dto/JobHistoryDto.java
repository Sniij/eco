package com.homework.eco.job.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

public class JobHistoryDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response{
        private int employeeId;
        private Date startDate;
        private Date endDate;
        private String jobId;
        private int departmentId;

        @Builder
        public Response(int employeeId, Date startDate, Date endDate, String jobId, int departmentId) {
            this.employeeId = employeeId;
            this.startDate = startDate;
            this.endDate = endDate;
            this.jobId = jobId;
            this.departmentId = departmentId;
        }

    }
}
