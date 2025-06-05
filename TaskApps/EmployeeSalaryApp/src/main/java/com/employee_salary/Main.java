package com.employee_salary;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Employee Salary Management System!");
        // You can add more functionality here as needed
        
        // For John Doe, a full-time employee
        // and Jane Smith, a part-time employee.

        System.out.println("======================================================");
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee( "December","John Doe", 101, 5000.00,200.00, 2000.00);
        fullTimeEmployee.calculateSalary();
        fullTimeEmployee.displayEmployeeDetails();

        System.out.println("--------------------------------------------------------");
        System.out.println("Full-time employee salary details for April:");
        
        FullTimeEmployee aprilFullTimeEmployee = new FullTimeEmployee("April", "John Doe", 101, 5000.00, 200.00, 2000.00);
        aprilFullTimeEmployee.calculateSalary();
        aprilFullTimeEmployee.displayEmployeeDetails();

        System.out.println("======================================================");

        PartTimeEmployee partTimeEmployee = new PartTimeEmployee("Jane Smith", 102, 20.00, 160);
        partTimeEmployee.calculateSalary();
        partTimeEmployee.displayEmployeeDetails();

        System.out.println("======================================================");
    }
}