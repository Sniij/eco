package com.homework.eco.employee.service;

import com.homework.eco.employee.entity.Employee;
import com.homework.eco.employee.repository.EmployeeRepository;
import com.homework.eco.exception.BusinessLogicException;
import com.homework.eco.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
}
