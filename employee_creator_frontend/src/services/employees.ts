type ContractType =
    | "FULL_TIME"
    | "PART_TIME"
    | "CONTRACT"
    | "TEMPORARY"
    | "INTERN";

interface Employee{
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    address: string;
    contractType: ContractType;
    jobTitle: string;
    startDate: string;
}

interface CreateEmployeeDTO {
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    address: string;
    contractType: ContractType;
    jobTitle: string;
    startDate: string;
}
interface UpdateEmployeeDTO {
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    address: string;
    contractType: ContractType;
    jobTitle: string;
    startDate: string;
}

export async function getAllEmployees(){
    const response = await fetch("http://localhost:8080/employees");
    if (!response.ok){
        throw new Error("Could not fetch employees")
    }
    return (await response.json()) as Employee[];
}

export async function createEmployee(employeeData: CreateEmployeeDTO) {
    const response = await fetch("http://localhost:8080/employees", {
        method: "POST",
        body: JSON.stringify(employeeData),
        headers: {"Content-Type": "application/json"},
    });

    if(!response.ok){
        throw new Error("Could not create employee");
    }
    return (await response.json()) as Employee;
}

export async function deleteEmployee(id: number){
    const response = await fetch(`http://localhost:8080/employees/${id}`, {
            method: "DELETE",
        });

    if (!response.ok) {
        throw new Error("Failed to delete employee");
    }
}

export async function editEmployee(id:number, employeeData: UpdateEmployeeDTO){
    const response = await fetch(`http://localhost:8080/employees/${id}`, {
            method: "PATCH",
            body: JSON.stringify(employeeData),
            headers: {"Content-Type": "application/json"},
        });

    if (!response.ok) {
        throw new Error("Failed to update employee");
    }

}