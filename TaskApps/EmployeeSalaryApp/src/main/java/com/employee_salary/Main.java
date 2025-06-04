package com.employee_salary;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Employee Salary Management System!");
        // You can add more functionality here as needed
        
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("John Doe", 101, 5000.00);
        fullTimeEmployee.calculateSalary();
        fullTimeEmployee.displayEmployeeDetails();
        System.out.println("======================================================");

        PartTimeEmployee partTimeEmployee = new PartTimeEmployee("Jane Smith", 102, 20.00, 160);
        partTimeEmployee.calculateSalary();
        partTimeEmployee.displayEmployeeDetails();

        System.out.println("======================================================");
    }
}