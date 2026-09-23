package io.nology.project.employee.entity;

import java.time.LocalDate;

import io.nology.project.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;


@Entity 
@Table(name = "employees")
public class Employee extends BaseEntity{


    @Column (nullable = false)
    private String firstName;

    @Column (nullable = false)
    private String lastName;
    
    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false, unique = true)
    private String phoneNumber;

    @Column (nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column
    private ContractType contractType;
    
    @Column (nullable = false)
    private String jobTitle;

    @Column (nullable = false)
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

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
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
