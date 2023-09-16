package com.homework.eco.employee.repository;

import com.homework.eco.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM employees WHERE employee_id = :id")
    Optional<Employee> findEmployeeById(@Param("id")int employeeId);

    @Query(nativeQuery = true, value = "SELECT * FROM employees WHERE department_id = :department_id")
    List<Employee> findEmployeeListByDepartmentId(@Param("department_id")int departmentId);
}
