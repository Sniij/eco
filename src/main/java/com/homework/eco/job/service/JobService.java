package com.homework.eco.job.service;

import com.homework.eco.employee.entity.Employee;
import com.homework.eco.exception.BusinessLogicException;
import com.homework.eco.exception.ExceptionCode;
import com.homework.eco.job.entity.Job;
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

    /**
     * job history 목록을 찾는 메서드
     * argument : employee Id
     * */
    public List<JobHistory> findHistories(int employeeId) {

        return jobHistoryRepository.findEmployeeJobHistory(employeeId);
    }

    /**
     * job 을 찾는 메서드
     * argument : job Id
     * */
    public Job findJob(String jobId){
        return findVerifiedJob(jobId);
    }

    /**
     * Job 검증 메서드
     * */
    private Job findVerifiedJob(String jobId) {

        Optional<Job> job =  jobRepository.findById(jobId);

        return job.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUND)
        );
    }
}
