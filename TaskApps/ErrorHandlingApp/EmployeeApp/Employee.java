

public class Employee{
    String employeeName;
    String employeeNumber;
    double netSalary;

    // Constructor without Validation

    // public Employee(String employeeName, String employeeNumber, double netSalary){
    //     // 
    //     this.employeeName = employeeName;
    //     this.employeeNumber = employeeNumber;
    //     this.netSalary = netSalary;
    // }

    // Constructor with Validation
    public Employee(String employeeName, String employeeNumber, double netSalary)   
         throws EmployeeDataException,IllegalArgumentException{
            setEmployeeName(employeeName);
            setEmployeeNumber(employeeNumber);
            setNetSalary(netSalary);
         }
    
    // Getters and Setters
    public String getEmployeeName(){
        return  employeeName;
    }

    public void setEmployeeName(String employeeName) throws  EmployeeDataException{
        if (employeeName.length() < 3) {
            throw  new EmployeeDataException("Invalid name, employee  names must be 3 characters long");   
        }
        else{
            this.employeeName = employeeName;
        }
        
    }

    // Employee Number
    public  String getEmployeeNumber(){
        return employeeNumber;
    }
    public void setEmployeeNumber(String employeeNumber) throws  EmployeeDataException{
        if (employeeNumber.length() > 5) {
            throw new EmployeeDataException("The employeeNumber should not exceed 5");    
        }
        else{
            this.employeeNumber = employeeNumber;
        }
        
    }

    // Employee netSalary
    public double getNetSalary(){
        return netSalary;
    }
    public void setNetSalary(double netSalary){
        if(netSalary < 0) {
            throw new IllegalArgumentException("The net salary cannot be less than 0");
        }
        else{
            this.netSalary = netSalary;
        }
        
    }


    @Override
    public String toString(){
        return "Employee [Number =  " + employeeNumber +
                ", Name :" + employeeName +
                "\n Net Salary" +netSalary +
                "\n";
    }

}