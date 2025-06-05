package com.employee_salary;
/**
 * FullTimeEmployee class represents a full-time employee in the Employee Salary Management System.
 * It extends the Employee class and provides specific implementations for full-time employees.
 */

public class FullTimeEmployee extends Employee {

    private double MonthlySalary;
    private double EmployeeBonus;
    private double EmployeeBenefits;
    final private String month;

    public FullTimeEmployee( String month, String EmployeeName, int EmployeeId, double MonthlySalary, double EmployeeBonus, double EmployeeBenefits) {
        super(EmployeeName, EmployeeId);
        this.MonthlySalary = MonthlySalary;
        this.EmployeeBenefits = EmployeeBenefits;
        this.EmployeeBonus = EmployeeBonus;
        this.month = month;
    }

    public double getMonthlySalary() {
        return MonthlySalary;
    }
    public void setMonthlySalary(double MonthlySalary) {
        this.MonthlySalary = MonthlySalary;
    }

    public double getEmployeeBenefits(){
        return EmployeeBenefits;
    }

    public void setEmployeeBenefits(double EmployeeBenefits){
        this.EmployeeBenefits = EmployeeBenefits;
    }
   
    // In case it is  December Salary
    public double getEmployeeBonus(){
        return EmployeeBonus;
    }

    public void setEmployeebonus(double EmployeeBonus){
        this.EmployeeBonus = EmployeeBonus;
    }


    @Override
    public double calculateSalary() {
        // For full-time employees, the salary is simply the monthly salary plus th
        // Employee benefits and the bonus if it is the end of the year.
       double TotalSalary = MonthlySalary + EmployeeBenefits;

        if ("December".equalsIgnoreCase(month)) {
            TotalSalary += EmployeeBonus; 
        }
        else {
            TotalSalary += 0; // No bonus for other months
        }
       

        setNetSalary(TotalSalary);
        return getNetSalary();
    }

    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        System.out.println("Monthly Salary: " + MonthlySalary);
        System.out.println("Net Salary: " + getNetSalary());
    }
  
}