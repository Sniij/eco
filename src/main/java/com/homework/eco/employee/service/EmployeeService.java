package com.homework.eco.employee.service;

import com.homework.eco.department.entity.Department;
import com.homework.eco.department.service.DepartmentService;
import com.homework.eco.employee.dto.EmployeeDto;
import com.homework.eco.employee.entity.Employee;
import com.homework.eco.employee.repository.EmployeeRepository;
import com.homework.eco.exception.BusinessLogicException;
import com.homework.eco.exception.ExceptionCode;
import com.homework.eco.job.entity.Job;
import com.homework.eco.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final JobService jobService;

    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, JobService jobService, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.jobService = jobService;
        this.departmentService = departmentService;
    }

    /**
     * Employee 찾는 메서드
     * verified 메서드를 통해 에러 핸들링
     * */
    public Employee findEmployee(int employeeId) {

        return findVerifiedEmployee(employeeId);
    }

    /**
     * Employee 검증 메서드
     * */
    private Employee findVerifiedEmployee(int employeeId) {
        Optional<Employee> employee = employeeRepository.findEmployeeById(employeeId);

        return employee.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUND)
        );
    }

    /**
     * Employee update 메서드
     * arguments: employee Patch request body
     * */
    public Employee updateEmployee(EmployeeDto.Patch requestBody) {

        Employee employee = findEmployee(requestBody.getEmployeeId());

        Job job = jobService.findJob(requestBody.getJobId());
        Employee manager = findEmployee(requestBody.getManagerId());
        Department department = departmentService.findDepartment(requestBody.getDepartmentId());

        Optional.ofNullable(requestBody.getFirstName())
                .ifPresent(firstName -> employee.setFirstName(firstName));
        Optional.ofNullable(requestBody.getLastName())
                .ifPresent(lastName -> employee.setLastName(lastName));
        Optional.ofNullable(requestBody.getEmail())
                .ifPresent(email -> employee.setEmail(email));
        Optional.ofNullable(requestBody.getPhoneNumber())
                .ifPresent(phoneNumber -> employee.setPhoneNumber(phoneNumber));
        Optional.ofNullable(requestBody.getHireDate())
                .ifPresent(date -> employee.setHireDate(date));
        Optional.ofNullable(job)
                .ifPresent(jobId -> employee.setJobId(jobId));
        Optional.ofNullable(requestBody.getSalary())
                .ifPresent(salary -> employee.setSalary(salary));
        Optional.ofNullable(requestBody.getCommissionPct())
                .ifPresent(commissionPct -> employee.setCommissionPct(commissionPct));
        Optional.ofNullable(manager)
                .ifPresent(managerId -> employee.setManagerId(managerId));
        Optional.ofNullable(department)
                .ifPresent(departmentId -> employee.setDepartmentId(departmentId) );


        return employeeRepository.save(employee);
    }


    /**
     * Employee List 찾는 메서드
     * arguments : departmentId
     */

    public List<Employee> findEmployeeByDepartmentId(int departmentId) {

        return employeeRepository.findEmployeeListByDepartmentId(departmentId);
    }


    /**
     * Employee List 를 저장시키는 메서드
     * arguments : Employee List
     */
    public List<Employee> updateEmployeeList(List<Employee> employeeList) {
        return employeeRepository.saveAll(employeeList);
    }
}
