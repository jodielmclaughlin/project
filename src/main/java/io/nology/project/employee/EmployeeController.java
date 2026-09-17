package io.nology.project.employee;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.project.common.exception.NotFoundException;
import io.nology.project.employee.dtos.CreateEmployeeDTO;
import io.nology.project.employee.dtos.EmployeeResponseDTO;
import io.nology.project.employee.dtos.UpdateEmployeeDTO;
import io.nology.project.employee.entity.Employee;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/employees")
//@Tag(name = "Employee Controller", description = "Employees endpoint")
public class EmployeeController{

    private static final Logger log = LogManager.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<Employee> all = this.employeeService.getAll();
        return ResponseEntity.ok(EmployeeResponseDTO.fromEntity(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        log.info("Getting employee with ID: " + id);
        Employee foundEmployee = this.employeeService.getById(id).orElseThrow(() -> new NotFoundException("Employee not found with ID: " + id));
        return ResponseEntity.ok(foundEmployee);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody @Valid CreateEmployeeDTO data){
        Employee created = this.employeeService.create(data);
        return new ResponseEntity<>(EmployeeResponseDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateEmployeeDTO data){
        Employee updated = this.employeeService.update(id, data).orElseThrow(() -> new NotFoundException("Employee not found with ID: " + id));

        return ResponseEntity.ok(EmployeeResponseDTO.fromEntity(updated));
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean successfullyDeleted = this.employeeService.delete(id);

        if(successfullyDeleted == false){
            throw new NotFoundException("Employee not found with ID: " + id);
        }

        return ResponseEntity.noContent().build();
    }
    
    

}