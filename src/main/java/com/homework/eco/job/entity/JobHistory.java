package com.homework.eco.job.entity;

import com.homework.eco.department.entity.Department;
import com.homework.eco.employee.entity.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Entity(name = "job_history")
public class JobHistory implements Serializable {

    @EmbeddedId
    private JobHistoryId jobHistoryId;

    @Column(name = "end_date", columnDefinition = "DATE NOT NULL")
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job jobId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department departmentId;

}