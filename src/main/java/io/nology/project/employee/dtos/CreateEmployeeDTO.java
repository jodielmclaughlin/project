package io.nology.project.employee.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class CreateEmployeeDTO{
    @NotBlank 
    private String firstName;

    @NotBlank 
    private String lastName;

    @NotBlank 
    private String email;

    @NotBlank 
    private String phoneNumber;

    @NotBlank 
    private String address;

    @NotBlank 
    private String contractType;

    @NotBlank 
    private String jobTitle;

    @NotBlank 
    private LocalDate startDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    
}
