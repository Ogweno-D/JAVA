// ========== CRASH COURSE CONCEPTS ==========

/*
1. ABSTRACT CLASSES:
   - Cannot be instantiated directly
   - Can contain both abstract and concrete methods
   - Abstract methods have no implementation (must be overridden)
   - Concrete methods have implementation (can be inherited or overridden)
   - Use 'abstract' keyword for class and methods

2. INHERITANCE:
   - Child classes inherit properties and methods from parent class
   - Use 'extends' keyword
   - Child classes can access parent's non-private members
   - Promotes code reusability

3. METHOD OVERRIDING:
   - Child class provides specific implementation of parent's abstract method
   - Use @Override annotation (good practice)
   - Method signature must match exactly

4. POLYMORPHISM:
   - Same method name behaves differently in different classes
   - Achieved through method overriding
   - Enables flexible and extensible code

5. ENCAPSULATION:
   - Bundling data and methods together
   - Using access modifiers (private, protected, public)
   - Protecting internal state of objects
*/

// ========== IMPLEMENTATION ==========

// Abstract parent class
// abstract class Employees {
//     // Properties (instance variables)
//     protected int employeeNumber;
//     protected String employeeName;
//     protected double netSalary;
    
//     // Constructor
//     public Employees(int employeeNumber, String employeeName) {
//         this.employeeNumber = employeeNumber;
//         this.employeeName = employeeName;
//         this.netSalary = 0.0; // Will be calculated by child classes
//     }
    
//     // Abstract method - must be implemented by child classes
//     public abstract void calculateSalary();
    
//     // Concrete method - inherited by all child classes
//     public void displayEmployeeDetails() {
//         System.out.println("Employee Number: " + employeeNumber);
//         System.out.println("Employee Name: " + employeeName);
//     }
    
//     // Getter methods
//     public double getNetSalary() {
//         return netSalary;
//     }
    
//     public int getEmployeeNumber() {
//         return employeeNumber;
//     }
    
//     public String getEmployeeName() {
//         return employeeName;
//     }
// }

// // Concrete child class for Full-Time Employees
// class FullTimeEmployee extends Employees {
//     private double baseSalary;
//     private double bonus;
//     private double benefits;
    
//     // Constructor
//     public FullTimeEmployee(int employeeNumber, String employeeName, 
//                            double baseSalary, double bonus, double benefits) {
//         super(employeeNumber, employeeName); // Call parent constructor
//         this.baseSalary = baseSalary;
//         this.bonus = bonus;
//         this.benefits = benefits;
//     }
    
//     // Implementation of abstract method
//     @Override
//     public void calculateSalary() {
//         // Full-time salary = base + bonus + benefits
//         this.netSalary = baseSalary + bonus + benefits;
//         System.out.println("\n=== FULL-TIME EMPLOYEE SALARY CALCULATION ===");
//         System.out.println("Base Salary: $" + baseSalary);
//         System.out.println("Bonus: $" + bonus);
//         System.out.println("Benefits: $" + benefits);
//         System.out.println("Net Salary: $" + netSalary);
//     }
    
//     // Override to show more details for full-time employees
//     @Override
//     public void displayEmployeeDetails() {
//         System.out.println("\n=== FULL-TIME EMPLOYEE DETAILS ===");
//         super.displayEmployeeDetails(); // Call parent method
//         System.out.println("Employee Type: Full-Time");
//         System.out.println("Base Salary: $" + baseSalary);
//         System.out.println("Monthly Bonus: $" + bonus);
//         System.out.println("Benefits Package: $" + benefits);
//     }
// }

// Concrete child class for Part-Time Employees
// class PartTimeEmployee extends Employees {
//     private double hourlyRate;
//     private int hoursWorked;
    
//     // Constructor
//     public PartTimeEmployee(int employeeNumber, String employeeName, 
//                            double hourlyRate, int hoursWorked) {
//         super(employeeNumber, employeeName); // Call parent constructor
//         this.hourlyRate = hourlyRate;
//         this.hoursWorked = hoursWorked;
//     }
    
//     // Implementation of abstract method
//     @Override
//     public void calculateSalary() {
//         // Part-time salary = hourly rate × hours worked
//         this.netSalary = hourlyRate * hoursWorked;
//         System.out.println("\n=== PART-TIME EMPLOYEE SALARY CALCULATION ===");
//         System.out.println("Hourly Rate: $" + hourlyRate);
//         System.out.println("Hours Worked: " + hoursWorked);
//         System.out.println("Net Salary: $" + netSalary);
//     }
    
//     // Override to show more details for part-time employees
//     @Override
//     public void displayEmployeeDetails() {
//         System.out.println("\n=== PART-TIME EMPLOYEE DETAILS ===");
//         super.displayEmployeeDetails(); // Call parent method
//         System.out.println("Employee Type: Part-Time");
//         System.out.println("Hourly Rate: $" + hourlyRate);
//         System.out.println("Hours Worked: " + hoursWorked + " hours");
//     }
// }

// // Main class to demonstrate the system
// public class EmployeeManagementSystem {
//     public static void main(String[] args) {
//         System.out.println("========== EMPLOYEE MANAGEMENT SYSTEM ==========\n");
        
//         // Create Full-Time Employee
//         FullTimeEmployee fullTimeEmp = new FullTimeEmployee(
//             1001, 
//             "John Smith", 
//             5000.0,  // base salary
//             1000.0,  // bonus
//             800.0    // benefits
//         );
        
//         // Create Part-Time Employee
//         PartTimeEmployee partTimeEmp = new PartTimeEmployee(
//             2001, 
//             "Jane Doe", 
//             25.0,    // hourly rate
//             120      // hours worked
//         );
        
//         // Display employee details
//         fullTimeEmp.displayEmployeeDetails();
//         partTimeEmp.displayEmployeeDetails();
        
//         // Calculate salaries
//         fullTimeEmp.calculateSalary();
//         partTimeEmp.calculateSalary();
        
//         // Summary
//         System.out.println("\n========== SALARY SUMMARY ==========");
//         System.out.println(fullTimeEmp.getEmployeeName() + " (Full-Time): $" + 
//                           fullTimeEmp.getNetSalary());
//         System.out.println(partTimeEmp.getEmployeeName() + " (Part-Time): $" + 
//                           partTimeEmp.getNetSalary());
        
//         // Demonstrate polymorphism
//         System.out.println("\n========== POLYMORPHISM DEMONSTRATION ==========");
//         Employees[] employees = {fullTimeEmp, partTimeEmp};
        
//         for (Employees emp : employees) {
//             System.out.println("\nProcessing employee: " + emp.getEmployeeName());
//             emp.calculateSalary(); // Same method call, different behavior
//         }
//     }
// }

/* 
========== EXPECTED OUTPUT ==========

=== FULL-TIME EMPLOYEE DETAILS ===
Employee Number: 1001
Employee Name: John Smith
Employee Type: Full-Time
Base Salary: $5000.0
Monthly Bonus: $1000.0
Benefits Package: $800.0

=== PART-TIME EMPLOYEE DETAILS ===
Employee Number: 2001
Employee Name: Jane Doe
Employee Type: Part-Time
Hourly Rate: $25.0
Hours Worked: 120 hours

=== FULL-TIME EMPLOYEE SALARY CALCULATION ===
Base Salary: $5000.0
Bonus: $1000.0
Benefits: $800.0
Net Salary: $6800.0

=== PART-TIME EMPLOYEE SALARY CALCULATION ===
Hourly Rate: $25.0
Hours Worked: 120
Net Salary: $3000.0

========== SALARY SUMMARY ==========
John Smith (Full-Time): $6800.0
Jane Doe (Part-Time): $3000.0
*/