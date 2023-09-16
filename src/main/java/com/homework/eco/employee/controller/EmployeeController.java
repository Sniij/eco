package com.homework.eco.employee.controller;

import com.homework.eco.dto.MultiResponseDto;
import com.homework.eco.dto.SingleResponseDto;
import com.homework.eco.employee.dto.EmployeeDto;
import com.homework.eco.employee.entity.Employee;
import com.homework.eco.employee.mapper.EmployeeMapper;
import com.homework.eco.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @PatchMapping
    public ResponseEntity patchEmployee(@RequestBody EmployeeDto.Patch requestBody){

        Employee employee = employeeService.updateEmployee(requestBody);

        EmployeeDto.Detail response = employeeMapper.entityToDetailResponse(employee);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @PatchMapping("/{departmentId}")
    public ResponseEntity patchDepartmentSalary(@PathVariable("departmentId") int departmentId,
                                                @RequestParam BigDecimal increment){

        List<Employee> employeeList = employeeService.findEmployeeByDepartmentId(departmentId);
        employeeList.stream().forEach(employee ->
                employee.setSalary(employee.getSalary().multiply(increment))
        );

        List<Employee> updatedEmployee = employeeService.updateEmployeeList(employeeList);

        List<EmployeeDto.Detail> responseList = employeeMapper.entityListToDetailList(updatedEmployee);

        return new ResponseEntity<>(new SingleResponseDto<>(responseList),HttpStatus.OK);
    }

}
