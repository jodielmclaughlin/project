package io.nology.project.employee;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import io.nology.project.employee.dtos.CreateEmployeeDTO;
import io.nology.project.employee.dtos.UpdateEmployeeDTO;
import io.nology.project.employee.entity.Employee;

@Service 
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    
    public EmployeeService(EmployeeRepository employeeRepo, ModelMapper modelMapper){
        this.employeeRepository = employeeRepo;
        this.modelMapper = modelMapper;
    }

    public List<Employee> getAll(){
        return this.employeeRepository.findAll();
    }

    public Optional<Employee> getById(Long id){
        return this.employeeRepository.findById(id);
    }

    public Employee create(CreateEmployeeDTO data){
        Employee newEmployee = modelMapper.map(data, Employee.class);
        this.employeeRepository.save(newEmployee);
        return newEmployee;
    }

    public Optional<Employee> update(Long id, UpdateEmployeeDTO data){
        Optional<Employee> result = this.employeeRepository.findById(id);

        if(result.isEmpty()) {
            return result;
        }
        Employee toUpdate = result.get();

        modelMapper.map(data, toUpdate);
        this.employeeRepository.save(toUpdate);
        return Optional.of(toUpdate);
    }

    public boolean delete(Long id){
        Optional<Employee> result = this.employeeRepository.findById(id);

        if(result.isEmpty()){
            return false;
        }
        this.employeeRepository.delete(result.get());
        return true;
    }


}