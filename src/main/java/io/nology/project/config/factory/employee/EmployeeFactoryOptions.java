package io.nology.project.config.factory.employee;

import java.time.LocalDate;

import io.nology.project.employee.entity.ContractType;

public class EmployeeFactoryOptions {
    String firstName;
    String lastName; 
    String email;
    String phoneNumber;
    String address;
    ContractType contractType;
    String jobTitle;
    LocalDate startDate;

    private EmployeeFactoryOptions(Builder builder){

        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.contractType = builder.contractType;
        this.jobTitle = builder.jobTitle;
        this.startDate = builder.startDate;

    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private String firstName;
        private String lastName; 
        private String email;
        private String phoneNumber;
        private String address;
        private ContractType contractType;
        private String jobTitle;
        private LocalDate startDate;

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

         public Builder email(String email){
            this.email = email;
            return this;
        }

         public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

         public Builder address(String address){
            this.address = address;
            return this;
        }

         public Builder contractType(ContractType contractType){
            this.contractType = contractType;
            return this;
        }

         public Builder jobTitle(String jobTitle){
            this.jobTitle = jobTitle;
            return this;
        }

         public Builder startDate(LocalDate startDate){
            this.startDate = startDate;
            return this;
        }

        public EmployeeFactoryOptions build() {
            return new EmployeeFactoryOptions(this);
        }

    }

}
