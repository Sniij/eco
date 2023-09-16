package com.homework.eco.department.service;

import com.homework.eco.department.entity.Department;
import com.homework.eco.department.repository.DepartmentRepository;
import com.homework.eco.exception.BusinessLogicException;
import com.homework.eco.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department findDepartment(int departmentId){

        return findVerifiedDepartment(departmentId);
    }

    private Department findVerifiedDepartment(int departmentId) {
        Optional<Department> department = departmentRepository.findDepartmentById(departmentId);
        return department.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUND)
        );
    }
}
