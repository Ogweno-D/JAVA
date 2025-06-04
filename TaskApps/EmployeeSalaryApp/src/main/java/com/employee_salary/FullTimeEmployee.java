package com.employee_salary;
/**
 * FullTimeEmployee class represents a full-time employee in the Employee Salary Management System.
 * It extends the Employee class and provides specific implementations for full-time employees.
 */

public class FullTimeEmployee extends Employee {

    private double MonthlySalary;

    public FullTimeEmployee(String EmployeeName, int EmployeeId, double MonthlySalary) {
        super(EmployeeName, EmployeeId);
        this.MonthlySalary = MonthlySalary;
    }

    public double getMonthlySalary() {
        return MonthlySalary;
    }
    public void setMonthlySalary(double MonthlySalary) {
        this.MonthlySalary = MonthlySalary;
    }
   
    @Override
    public double calculateSalary() {
        // For full-time employees, the salary is simply the monthly salary
        setNetSalary(MonthlySalary);
        return getNetSalary();
    }
    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        System.out.println("Monthly Salary: " + MonthlySalary);
        System.out.println("Net Salary: " + getNetSalary());
    }
  
}