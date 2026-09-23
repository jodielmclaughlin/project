package io.nology.project.employee;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import io.nology.project.config.factory.employee.EmployeeFactory;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class EmployeeE2ETest {

    @LocalServerPort
    private int port;

    private EmployeeFactory employeeFactory;

    @Autowired 
    public EmployeeE2ETest(EmployeeFactory employeeFactory){
        this.employeeFactory = employeeFactory;
    }

    @BeforeEach 
    void setUp() {
        RestAssured.port = port;
    }

    //Tests to do
    //getAllEmployees_whenNoEmployees_returnsEmptyArray
    //getAllEmployees_whenEmployeesInDB_returnsAllEmployees
    //getById_validId_returnsEmployee
    //getById_nonExistentId_returns404
    //getById_invalidDataTypeId_returnsBadRequest
    //createEmployee_withMissingData_ReturnsBadRequest
    //createEmployee_withValidData_ReturnsCreatedEmployee
    //updateEmployee_withInvalidData_ReturnsBadRequest
    //updateEmployee_EmployeeDoesNotExist_ReturnsNotFound
    //updateEmployee_withValidData_ReturnsOK
    //deleteEmployee_EmployeeDoesNotExist_ReturnsNotFound
    //deleteEmployee_EmployeeExists_ReturnsNoContent

    
}
