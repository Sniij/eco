package com.homework.eco.department.controller;

import com.homework.eco.department.dto.DepartmentDto;
import com.homework.eco.department.entity.Department;
import com.homework.eco.department.mapper.DepartmentMapper;
import com.homework.eco.department.service.DepartmentService;
import com.homework.eco.dto.SingleResponseDto;
import com.homework.eco.location.dto.LocationDto;
import com.homework.eco.location.entity.Location;
import com.homework.eco.location.mapper.LocationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    private final LocationMapper locationMapper;


    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper, LocationMapper locationMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
        this.locationMapper = locationMapper;
    }


    @GetMapping("/{departmentId}")
    public ResponseEntity getDepartment(@PathVariable("departmentId") int departmentId){
        Department department = departmentService.findDepartment(departmentId);
        DepartmentDto.Detail response = departmentMapper.entityToDetail(department);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/location/{departmentId}")
    public ResponseEntity getDepartmentLocation(@PathVariable("departmentId") int departmentId){
        Department department = departmentService.findDepartment(departmentId);

        Location location = department.getLocationId();
        LocationDto.Detail response = locationMapper.entityToDetail(location);
        response.setDepartmentName( department.getDepartmentName() );

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK );
    }
}
