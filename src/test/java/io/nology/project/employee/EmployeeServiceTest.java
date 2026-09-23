package io.nology.project.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import io.nology.project.employee.dtos.CreateEmployeeDTO;
import io.nology.project.employee.dtos.UpdateEmployeeDTO;
import io.nology.project.employee.entity.Employee;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock 
    private EmployeeRepository employeeRepository;
    @Mock 
    private ModelMapper modelMapper;

    @Spy 
    @InjectMocks 
    private EmployeeService employeeService;

    //Tests to do
    @Test 
    public void getAll_CallsFindAllOnRepo(){
        this.employeeService.getAll();
        verify(this.employeeRepository).findAll();
    }

    @Test 
    public void getById_CallsFindByIdWithRightArg(){
        this.employeeService.getById(1l);
        verify(this.employeeRepository).findById(1l);
    }

    @Test 
    public void createEmployee_savesOutputOfMapper(){
        CreateEmployeeDTO dto = new CreateEmployeeDTO();
        Employee fakeEmployee = new Employee();
        when(this.modelMapper.map(dto, Employee.class)).thenReturn(fakeEmployee);
        this.employeeService.create(dto);

        verify(this.employeeRepository).save(fakeEmployee);
    }

    @Test
    public void updateEmployee_whenEmployeeDoesNotExist_returnsEmptyOptionalDoNotSave(){
        when(this.employeeRepository.findById(1l)).thenReturn(Optional.empty());
        Optional<Employee> result = this.employeeService.update(1l, new UpdateEmployeeDTO());
        assertTrue(result.isEmpty());
        verifyNoInteractions(this.modelMapper);
        verify(this.employeeRepository, never()).save(any(Employee.class));

    }
     @Test
    public void updateEmployee_whenEmployeeExists_savesResultOfMapping(){
        UpdateEmployeeDTO dto = new UpdateEmployeeDTO();
        Employee fakeEmployee = new Employee();
        when(this.employeeRepository.findById(1l)).thenReturn(Optional.of(fakeEmployee));
        Optional<Employee> result = this.employeeService.update(1l, dto);
        verify(this.modelMapper).map(dto, fakeEmployee);
        verify(this.employeeRepository).save(fakeEmployee);
        assertEquals(fakeEmployee, result.get());
    }


     @Test
    public void deleteEmployee_whenEmployeeDoesNotExist_returnsFalseAndDoesNotCallDelete(){
        when(this.employeeRepository.findById(1l)).thenReturn(Optional.empty());
        boolean result = this.employeeService.delete(1l);
        assertFalse(result);
        verify(this.employeeRepository, never()).delete(any(Employee.class));
    }

    @Test
    public void deleteEmployee_whenEmployeeExists_returnsTrueAndCallsDelete(){
        Employee fakeEmployee = new Employee();
        when(this.employeeRepository.findById(1l)).thenReturn(Optional.of(fakeEmployee));
        boolean result = this.employeeService.delete(1l);
        assertTrue(result);
        verify(this.employeeRepository).delete(fakeEmployee);
    }
    
}
