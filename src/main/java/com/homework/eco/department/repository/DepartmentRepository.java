package com.homework.eco.department.repository;

import com.homework.eco.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM departments WHERE department_id = :id")
    Optional<Department> findDepartmentById(@Param("id")int departmentId);


}
