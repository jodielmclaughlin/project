package io.nology.project.employee;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import io.nology.project.config.factory.employee.EmployeeFactory;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock 
    private EmployeeFactory employeeFactory;

    @Mock 
    private ModelMapper modelMapper;

    @Spy 
    @InjectMocks 
    private EmployeeService employeeService;

    //Tests to do
    //getAll_CallsFindAllOnRepo
    //getById_CallsFindByIdWithRightArg
    //createEmployee_savesOutputOfMapper
    //updateEmployee_whenEmployeeDoesNotExist_returnsEmptyOptionalDoNotSave
    //updateEmployee_whenEmployeeExists_savesResultOfMapping
    //deleteEmployee_whenEmployeeDoesNotExist_returnsFalseAndDoesNotCallDelete
    //deleteEmployee_whenEmployeeExists_returnsTrueAndCallsDelete
    
}
