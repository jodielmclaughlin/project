package io.nology.project.employee.dtos;

import java.time.LocalDate;
import java.util.List;

import io.nology.project.employee.entity.ContractType;
import io.nology.project.employee.entity.Employee;

public record EmployeeResponseDTO(Long id, String firstName, String lastName, String email, String phoneNumber, 
    String address, ContractType contractType, String jobTitle, LocalDate startDate) {

        public static EmployeeResponseDTO fromEntity(Employee employee){
            return new EmployeeResponseDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getAddress(),
                employee.getContractType(),
                employee.getJobTitle(),
                employee.getStartDate());
        }

        public static List<EmployeeResponseDTO> fromEntity(List<Employee> employeeList){
            return employeeList.stream().map(e -> EmployeeResponseDTO.fromEntity(e)).toList();
        }
    
}
