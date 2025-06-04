package com.employee_salary;

/**
 * Employee class represents a generic employee in the Employee Salary Management System.
 * It serves as a base class for different types of employees (e.g., full-time, part-time).
 */
public abstract class Employee {
    private String EmployeeName;
    private int EmployeeId;
    private double NetSalary;


    /**
     * Constructor to initialize the employee's name and ID.
     * @param EmployeeName Name of the employee
     * @param EmployeeId ID of the employee
     */
    public Employee(String EmployeeName, int EmployeeId) {
        this.EmployeeName = EmployeeName;
        this.EmployeeId = EmployeeId;
    }

    /**
     * Getters and Setters for employee attributes
     */
    public String getEmployeeName() {
        return EmployeeName;
    }
    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }
    public int getEmployeeId() {
        return EmployeeId;
    }
    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }
    public double getNetSalary() {
        return NetSalary;
    }
    public void setNetSalary(double NetSalary) {
        this.NetSalary = NetSalary;
    }

    /**
     * Abstract method to calculate the salary of the employee.
     * This method must be implemented by any subclass of Employee
    */
    public abstract double calculateSalary();

    /**
     * Method to display the details of the employee.
     * This method can be overridden by subclasses to provide specific details.
     */
    public void displayEmployeeDetails() {
        System.out.println("Employe e Name: " + EmployeeName);
        System.out.println("Employee ID: " + EmployeeId);
    }

}