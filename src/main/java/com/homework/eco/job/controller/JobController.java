package com.homework.eco.job.controller;


import com.homework.eco.dto.SingleResponseDto;
import com.homework.eco.job.dto.JobHistoryDto;
import com.homework.eco.job.entity.JobHistory;
import com.homework.eco.job.mapper.JobHistoryMapper;
import com.homework.eco.job.mapper.JobMapper;
import com.homework.eco.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    private final JobMapper jobMapper;

    private final JobHistoryMapper jobHistoryMapper;

    public JobController(JobService jobService, JobMapper jobMapper, JobHistoryMapper jobHistoryMapper) {
        this.jobService = jobService;
        this.jobMapper = jobMapper;
        this.jobHistoryMapper = jobHistoryMapper;
    }

    @GetMapping("/history/{employeeId}")
    public ResponseEntity getEmployeeHistory(
            @PathVariable("employeeId") int employeeId
    ){
        List<JobHistory> jobHistory = jobService.findHistories(employeeId);

        List<JobHistoryDto.Response> response = jobHistoryMapper.entityListToResponseList(jobHistory);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

}
