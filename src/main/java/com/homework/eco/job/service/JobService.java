package com.homework.eco.job.service;

import com.homework.eco.job.entity.JobHistory;
import com.homework.eco.job.repository.JobHistoryRepository;
import com.homework.eco.job.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobHistoryRepository jobHistoryRepository;

    public JobService(JobRepository jobRepository, JobHistoryRepository jobHistoryRepository) {
        this.jobRepository = jobRepository;
        this.jobHistoryRepository = jobHistoryRepository;
    }

    public List<JobHistory> findHistories(int employeeId) {

        return jobHistoryRepository.findEmployeeJobHistory(employeeId);
    }
}
