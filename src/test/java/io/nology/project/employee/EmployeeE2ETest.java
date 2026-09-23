package io.nology.project.employee;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.util.HashMap;

import io.nology.project.config.factory.employee.EmployeeFactory;
import io.nology.project.config.factory.employee.EmployeeFactoryOptions;
import io.nology.project.employee.entity.ContractType;
import io.nology.project.employee.entity.Employee;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

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
    @Test 
    public void getAllEmployees_whenNoEmployees_returnsEmptyArray(){
        given().when().get("/employees")
                .then().statusCode(HttpStatus.OK.value())
                .body("$", hasSize(0));
    }

     @Test 
     public void getAllEmployees_whenEmployeesInDB_returnsAllEmployees(){
        EmployeeFactoryOptions options = EmployeeFactoryOptions.builder().build();
        this.employeeFactory.create(options, 10);

        given().when().get("/employees")
                .then().statusCode(HttpStatus.OK.value())
                .body("$", hasSize(10))
                .body(matchesJsonSchemaInClasspath("schema/employee-list-schema.json"));
     }

    @Test 
    public void getById_validId_returnsEmployee(){
        EmployeeFactoryOptions options = EmployeeFactoryOptions.builder()
            .firstName("Jodie")
            .lastName("McLaughlin")
            .email("fake.email@google.com")
            .phoneNumber("07934988273")
            .address("Fake address, uk")
            .contractType(ContractType.FULL_TIME)
            .jobTitle("Test Engineer")
            .startDate(LocalDate.of(2020,5,15)).build();

        Employee jodie = this.employeeFactory.create(options);
        Long id = jodie.getId();

        given().when().get("/employees/" + id)
                .then().statusCode(HttpStatus.OK.value())
                .body("firstName", equalTo("Jodie"))
                .body("lastName", equalTo("McLaughlin"))
                .body("jobTitle",equalTo("Test Engineer"))
                .body(matchesJsonSchemaInClasspath("schema/employee-schema.json"));
    }

    @Test 
    public void getById_nonExistentId_returns404(){
        given().when().get("/employees/" + 1l)
                .then().statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(404))
                .body("error", equalTo("Not Found"))
                .body("message", containsString("Employee not found with ID: 1"))
                .body(matchesJsonSchemaInClasspath("schema/api-error-response-schema.json"));
    }

    @Test 
    public void getById_invalidDataTypeId_returnsBadRequest(){
        given().when().get("/employees/" + "apple")
                // assert
                .then().log().all().statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("error", equalTo("Bad Request"))
                .body("message", containsString("Failed to convert value"))
                .body(matchesJsonSchemaInClasspath("schema/api-error-response-schema.json"));
    }

    @Test 
    public void createEmployee_withMissingData_ReturnsBadRequest(){
        HashMap<String, String> data = new HashMap<>();
        data.put("firstName", "Jodie");
        data.put("lastName", "McLaughlin");

        given().contentType(ContentType.JSON).body(data).when().log().all()
                .post("/employees")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test 
    public void createEmployee_withValidData_ReturnsCreatedEmployee(){
        HashMap<String, String> data = new HashMap<>();
        data.put("firstName", "Jodie");
        data.put("lastName", "McLaughlin");
        data.put("email", "fake.email@google.com");
        data.put("phoneNumber","07934988273");
        data.put("address", "Fake address, uk");
        data.put("contractType", "FULL_TIME");
        data.put("jobTitle", "Test Engineer");
        data.put("startDate", "2008-10-07");

        given().contentType(ContentType.JSON).body(data).when().log().all()
                .post("/employees")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("firstName", equalTo("Jodie"))
                .body("lastName", equalTo("McLaughlin"))
                .body("jobTitle",equalTo("Test Engineer"))
                .body(matchesJsonSchemaInClasspath("schema/employee-schema.json"));
    }

    @Test 
    public void updateEmployee_withInvalidData_ReturnsBadRequest(){
        EmployeeFactoryOptions options = EmployeeFactoryOptions.builder().build();
        Employee fakeEmployee = this.employeeFactory.create(options);

        HashMap<String, String> data = new HashMap<>();
        data.put("firstName", "  ");
        given().contentType(ContentType.JSON).body(data).when().log().all()
                .patch("/employees/" + fakeEmployee.getId())
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test 
    public void updateEmployee_EmployeeDoesNotExist_ReturnsNotFound(){
        
        HashMap<String, String> data = new HashMap<>();
        data.put("firstName", "updated");

        given().contentType(ContentType.JSON).body(data).when().log().all()
                .patch("/employees/2")
                .then().log().all()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Employee not found with ID: 2"))
                .body(matchesJsonSchemaInClasspath("schema/api-error-response-schema.json"));
    }

    @Test 
    public void updateEmployee_withValidData_ReturnsOK(){
        EmployeeFactoryOptions options = EmployeeFactoryOptions.builder().build();
        Employee fakeEmployee = this.employeeFactory.create(options);

        HashMap<String, String> data = new HashMap<>();
        data.put("firstName", "updated");

        given().contentType(ContentType.JSON).body(data).when().log().all()
                .patch("/employees/" + fakeEmployee.getId())
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("firstName", equalTo("updated"))
                .body(matchesJsonSchemaInClasspath("schema/employee-schema.json"));
    }

    @Test 
    public void deleteEmployee_EmployeeDoesNotExist_ReturnsNotFound(){
        given()
                .when()
                .delete("/employees/1")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("message", equalTo("Employee not found with ID: 1"))
                .body(matchesJsonSchemaInClasspath("schema/api-error-response-schema.json"));
    }

    @Test 
    public void deleteEmployee_EmployeeExists_ReturnsNoContent(){
        EmployeeFactoryOptions options = EmployeeFactoryOptions.builder().build();
        Employee fakeEmployee = this.employeeFactory.create(options);

        given() 
                .when()
                .delete("/employees/" + fakeEmployee.getId())
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    
}
