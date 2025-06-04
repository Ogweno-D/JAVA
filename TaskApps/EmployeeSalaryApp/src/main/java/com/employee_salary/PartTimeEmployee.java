package com.employee_salary;

/**
 * PartTimeEmployee class represents a part-time employee in the Employee Salary Management System.
 * It extends the Employee class and provides specific implementations for part-time employees.
 */

public class PartTimeEmployee extends Employee{

    private double HourlyRate;
    private int HoursWorked;

    /**
     * Constructor to initialize the part-time employee's name, ID, hourly rate, and hours worked.
     * @param EmployeeName Name of the employee
     * @param EmployeeId ID of the employee
     * @param HourlyRate Hourly rate of the employee
     * @param HoursWorked Number of hours worked by the employee
     */
    public PartTimeEmployee(String EmployeeName, int EmployeeId, double HourlyRate, int HoursWorked) {
        super(EmployeeName, EmployeeId);
        this.HourlyRate = HourlyRate;
        this.HoursWorked = HoursWorked;
    }

    /**
     * Getters and Setters for part-time employee attributes
     */

     public double getHourlyRate() {
        return HourlyRate;
    }

    public void setHourlyRate(double HourlyRate) {
        this.HourlyRate = HourlyRate;
    }

    public int getHoursWorked() {
        return HoursWorked;
    }

    public void setHoursWorked(int HoursWorked) {
        this.HoursWorked = HoursWorked;
    }

    /**
     * Calculates the salary for the part-time employee based on hourly rate and hours worked.
     * The salary is set as the net salary of the employee.
     * @return The calculated net salary
     */

    @Override
    public double calculateSalary() {
        // For part-time employees, the salary is calculated based on hourly rate and hours worked
        double Salary = HourlyRate * HoursWorked;
        setNetSalary(Salary);
        return getNetSalary();
    }
    /**
     * Displays the details of the part-time employee including name, ID, hourly rate,
     * hours worked, and net salary.
     * This method overrides the displayEmployeeDetails method from the Employee class.
     */

    @Override
    public void displayEmployeeDetails() {
        super.displayEmployeeDetails();
        System.out.println("Hourly Rate: " + HourlyRate);
        System.out.println("Hours Worked: " + HoursWorked);
        System.out.println("Net Salary: " + getNetSalary());
    }

}