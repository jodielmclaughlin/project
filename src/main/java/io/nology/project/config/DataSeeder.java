package io.nology.project.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import io.nology.project.config.factory.employee.EmployeeFactory;

@Component 
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final EmployeeFactory employeeFactory;

    public DataSeeder(EmployeeFactory employeeFactory){
        this.employeeFactory = employeeFactory;
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        if(this.employeeFactory.repoEmpty()){

            employeeFactory.create(20);
        }

    }

}
