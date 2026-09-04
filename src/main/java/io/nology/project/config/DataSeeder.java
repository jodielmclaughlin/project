package io.nology.project.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import io.nology.project.employee.EmployeeRepository;
import io.nology.project.employee.entity.Employee;

@Component 
@Profile("dev")
public class DataSeeder implements CommandLineRunner {
    
    private final EmployeeRepository employeeRepository;
    private final Faker faker = new Faker(); 

    public DataSeeder(EmployeeRepository repo){
        this.employeeRepository = repo;
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        if(this.employeeRepository.count() == 0){
        Set<String> emails = new HashSet<>();
        List<Employee> employees = new ArrayList<>();

            while(employees.size() < 10) {
                String email = faker.internet().emailAddress();

                if(email.contains(email)){
                    continue;
                }

                emails.add(email);
                Employee employee = new Employee();
                employee.setFirstName(faker.name().firstName());
                employee.setLastName(faker.name().lastName());
                employee.setPhoneNumber(faker.phoneNumber().cellPhone());
                employee.setEmail(email);
                employees.add(employee);

            }
            this.employeeRepository.saveAllAndFlush(employees);
        }

    }

}
