package com.homework.eco.job.repository;

import com.homework.eco.job.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Integer> {

    @Query(value = "SELECT * FROM job_history WHERE employee_id = :id", nativeQuery = true)
    List<JobHistory> findEmployeeJobHistory(@Param("id") int employeeId);


}
