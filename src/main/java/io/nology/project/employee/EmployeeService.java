package io.nology.project.employee;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service 
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    
    public EmployeeService(EmployeeRepository employeeRepo, ModelMapper modelMapper){
        this.employeeRepository = employeeRepo;
        this.modelMapper = modelMapper;
    }

    

}