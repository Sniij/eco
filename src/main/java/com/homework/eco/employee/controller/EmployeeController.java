package com.homework.eco.employee.controller;

import com.homework.eco.dto.SingleResponseDto;
import com.homework.eco.employee.dto.EmployeeDto;
import com.homework.eco.employee.entity.Employee;
import com.homework.eco.employee.mapper.EmployeeMapper;
import com.homework.eco.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity getEmployee(@PathVariable("employeeId") int employeeId){

        Employee employee = employeeService.findEmployee(employeeId);

        EmployeeDto.Detail response = employeeMapper.entityToDetailResponse(employee);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

}
