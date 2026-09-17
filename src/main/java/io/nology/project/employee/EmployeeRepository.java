package io.nology.project.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nology.project.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll();
    
}