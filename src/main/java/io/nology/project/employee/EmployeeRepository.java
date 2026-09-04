package io.nology.project.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nology.project.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    
}