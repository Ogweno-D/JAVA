# Java Exception Handling

## Table of Contents

1. [Overview](#overview)
2. [Learning Objectives](#learning-objectives)
3. [Exception Handling Fundamentals](#exception-handling-fundamentals)
4. [Part A: Integer Validation](#part-a-integer-validation)
5. [Part B: Bank Account with Custom Exceptions](#part-b-bank-account-with-custom-exceptions)
6. [Part C: Employee Management with Multiple Validations](#part-c-employee-management-with-multiple-validations)
7. [Complete Source Code](#complete-source-code)
8. [How to Run](#how-to-run)
9. [Expected Output](#expected-output)
10. [Key Concepts Summary](#key-concepts-summary)

## Overview

This tutorial demonstrates comprehensive exception handling in Java through three practical examples:

- **Part A**: Basic input validation with try-catch
- **Part B**: Custom exceptions with banking operations
- **Part C**: Multiple exception types with employee data validation

## Learning Objectives

After completing this tutorial, you will understand:

- ✅ Basic try-catch-finally syntax
- ✅ Creating and using custom exceptions
- ✅ Handling multiple exception types in a single try block
- ✅ Input validation and error recovery
- ✅ Method-level exception throwing
- ✅ Exception hierarchy and best practices

## Exception Handling Fundamentals

### What is Exception Handling?

Exception handling is a programming mechanism that allows you to handle runtime errors gracefully without crashing your program.

### Basic Syntax

```java
try {
    // Code that might throw an exception
} catch (SpecificException e) {
    // Handle specific exception
} catch (GeneralException e) {
    // Handle general exception
} finally {
    // Code that always executes (cleanup)
}
```

### Exception Types

- **Checked Exceptions**: Must be declared or handled (e.g., IOException)
- **Unchecked Exceptions**: Runtime exceptions (e.g., NumberFormatException)
- **Custom Exceptions**: User-defined exceptions for specific business logic

---

## Part A: Integer Validation

### 🎯 **Objective**

Create a program that validates user input to ensure it's a valid integer. If invalid, catch the exception and allow retry.

### 📋 **Requirements**

- Validate integer input using try-catch
- Handle `NumberFormatException` for invalid input
- Square the valid integer and display result
- Allow user to retry on invalid input

### 💡 **Key Concepts**

- **Try-Catch Block**: Basic exception handling structure
- **NumberFormatException**: Thrown when string cannot be parsed as number
- **Input Validation Loop**: Continue until valid input received

### 📝 **Code Example**

```java
public static void partA_IntegerValidation() {
    Scanner scanner = new Scanner(System.in);
    boolean validInput = false;
    
    while (!validInput) {
        try {
            System.out.print("Enter an integer to square: ");
            String input = scanner.nextLine();
            
            // This will throw NumberFormatException if input is not valid
            int number = Integer.parseInt(input);
            
            int squared = number * number;
            System.out.println("The square of " + number + " is: " + squared);
            validInput = true;
            
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid input! Please enter a valid integer.");
            System.out.println("Examples: 5, -10, 0, 123");
        }
    }
}
```

### 🔍 **Explanation**

1. **Input Loop**: Continues until valid integer is entered
2. **Parse Attempt**: `Integer.parseInt()` throws `NumberFormatException` for invalid input
3. **Exception Handling**: Catches the exception and provides user feedback
4. **Success Path**: Calculates and displays the squared result

---

## Part B: Bank Account with Custom Exceptions

### 🎯 **Objective**

Create a `BankAccount` class with deposit/withdraw operations that handle various error conditions using custom exceptions.

### 📋 **Requirements**

- `BankAccount` class with balance field
- `deposit(double amount)` and `withdraw(double amount)` methods
- Custom `InsufficientFundsException`
- Handle invalid deposits (negative/zero amounts)
- Use finally block for transaction completion messages

### 💡 **Key Concepts**

- **Custom Exceptions**: Creating domain-specific exceptions
- **Method-level Exception Handling**: Throwing exceptions from methods
- **Finally Block**: Code that always executes regardless of exceptions
- **Multiple Exception Types**: Handling different error conditions

### 🏗️ **Custom Exception**

```java
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
```

### 🏦 **BankAccount Class Structure**

```java
class BankAccount {
    private double balance;
    
    // Constructors
    public BankAccount() { this.balance = 0.0; }
    public BankAccount(double initialBalance) { this.balance = initialBalance; }
    
    // Getters and Setters
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    
    // Methods with Exception Handling
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available: $" + balance);
        }
        balance -= amount;
    }
}
```

### 🔍 **Exception Handling Pattern**

```java
private static void testWithdrawal(BankAccount account, double amount) {
    try {
        System.out.println("Attempting to withdraw: $" + amount);
        account.withdraw(amount);
        System.out.println("Withdrawal successful!");
    } catch (InsufficientFundsException e) {
        System.out.println("WITHDRAWAL ERROR: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.out.println("INVALID AMOUNT: " + e.getMessage());
    } finally {
        System.out.println("Transaction complete. Balance: $" + account.getBalance());
    }
}
```

---

## Part C: Employee Management with Multiple Validations

### 🎯 **Objective**

Create an `Employee` class with comprehensive validation that demonstrates handling multiple exception types in a single try block.

### 📋 **Requirements**

- `Employee` class with fields: `employeeNumber`, `employeeName`, `netSalary`
- Custom `EmployeeDataException`
- Validation rules:
  - Employee number: max 5 characters
  - Employee name: min 3 characters
  - Net salary: must be positive number
- Single try block with multiple catch blocks

### 💡 **Key Concepts**

- **Multiple Catch Blocks**: Different handling for different exception types
- **Validation in Setters**: Business rule enforcement
- **Constructor Validation**: Ensuring object integrity
- **Exception Specificity**: Using appropriate exception types

### 🏗️ **Custom Exception**

```java
class EmployeeDataException extends Exception {
    public EmployeeDataException(String message) {
        super(message);
    }
}
```

### 👤 **Employee Class Structure**

```java
class Employee {
    private String employeeNumber;
    private String employeeName;
    private double netSalary;
    
    // Constructor with validation
    public Employee(String employeeNumber, String employeeName, double netSalary) 
            throws EmployeeDataException, NumberFormatException {
        setEmployeeNumber(employeeNumber);
        setEmployeeName(employeeName);
        setNetSalary(netSalary);
    }
    
    // Validated Setters
    public void setEmployeeNumber(String employeeNumber) throws EmployeeDataException {
        if (employeeNumber.length() > 5) {
            throw new EmployeeDataException("Employee number cannot exceed 5 characters");
        }
        this.employeeNumber = employeeNumber;
    }
    
    public void setEmployeeName(String employeeName) throws EmployeeDataException {
        if (employeeName.length() < 3) {
            throw new EmployeeDataException("Employee name must be at least 3 characters");
        }
        this.employeeName = employeeName;
    }
    
    public void setNetSalary(double netSalary) throws NumberFormatException {
        if (netSalary < 0) {
            throw new NumberFormatException("Net salary cannot be negative");
        }
        this.netSalary = netSalary;
    }
}
```

### 🔍 **Multiple Exception Handling**

```java
private static void createEmployee(String empNumber, String empName, String salaryStr) {
    try {
        // All validation happens in the same try block
        double salary = Double.parseDouble(salaryStr);      // NumberFormatException
        Employee employee = new Employee(empNumber, empName, salary); // EmployeeDataException
        
        System.out.println("SUCCESS: " + employee);
        
    } catch (EmployeeDataException e) {
        System.out.println("EMPLOYEE DATA ERROR: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println("SALARY FORMAT ERROR: Invalid salary format");
    } catch (Exception e) {
        System.out.println("UNEXPECTED ERROR: " + e.getMessage());
    }
}
```

---

## Complete Source Code

```java
import java.util.Scanner;

// Custom Exceptions
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class EmployeeDataException extends Exception {
    public EmployeeDataException(String message) {
        super(message);
    }
}

// BankAccount Class for Part B
class BankAccount {
    private double balance;
    
    public BankAccount() {
        this.balance = 0.0;
    }
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive and greater than zero");
        }
        balance += amount;
        System.out.println("Successfully deposited $" + amount + ". New balance: $" + balance);
    }
    
    public void withdraw(double amount) throws InsufficientFundsException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive and greater than zero");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: $" + balance);
        }
        balance -= amount;
        System.out.println("Successfully withdrew $" + amount + ". New balance: $" + balance);
    }
}

// Employee Class for Part C
class Employee {
    private String employeeNumber;
    private String employeeName;
    private double netSalary;
    
    public Employee() {}
    
    public Employee(String employeeNumber, String employeeName, double netSalary) 
            throws EmployeeDataException, NumberFormatException {
        setEmployeeNumber(employeeNumber);
        setEmployeeName(employeeName);
        setNetSalary(netSalary);
    }
    
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public double getNetSalary() {
        return netSalary;
    }
    
    public void setEmployeeNumber(String employeeNumber) throws EmployeeDataException {
        if (employeeNumber.length() > 5) {
            throw new EmployeeDataException("Employee number cannot exceed 5 characters");
        }
        this.employeeNumber = employeeNumber;
    }
    
    public void setEmployeeName(String employeeName) throws EmployeeDataException {
        if (employeeName.length() < 3) {
            throw new EmployeeDataException("Employee name must be at least 3 characters long");
        }
        this.employeeName = employeeName;
    }
    
    public void setNetSalary(double netSalary) throws NumberFormatException {
        if (netSalary < 0) {
            throw new NumberFormatException("Net salary cannot be negative");
        }
        this.netSalary = netSalary;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", netSalary=" + netSalary +
                '}';
    }
}

public class ExceptionHandlingTutorial {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== JAVA EXCEPTION HANDLING TUTORIAL ===\n");
        
        // Part A: Integer Validation
        System.out.println("PART A: Integer Validation and Squaring");
        partA_IntegerValidation();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Part B: Bank Account Operations
        System.out.println("PART B: Bank Account with Exception Handling");
        partB_BankAccount();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Part C: Employee Creation with Validation
        System.out.println("PART C: Employee Creation with Custom Exceptions");
        partC_EmployeeCreation();
        
        scanner.close();
    }
    
    // PART A: Integer Validation
    public static void partA_IntegerValidation() {
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print("Enter an integer to square: ");
                String input = scanner.nextLine();
                
                int number = Integer.parseInt(input);
                int squared = number * number;
                System.out.println("The square of " + number + " is: " + squared);
                validInput = true;
                
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input! Please enter a valid integer.");
                System.out.println("Examples of valid integers: 5, -10, 0, 123");
            }
        }
    }
    
    // PART B: Bank Account Operations
    public static void partB_BankAccount() {
        BankAccount account = new BankAccount(1000.0);
        System.out.println("Initial account balance: $" + account.getBalance());
        
        System.out.println("\n--- DEPOSIT OPERATIONS ---");
        testDeposit(account, 500.0);   // Valid
        testDeposit(account, -100.0);  // Invalid (negative)
        testDeposit(account, 0.0);     // Invalid (zero)
        
        System.out.println("\n--- WITHDRAWAL OPERATIONS ---");
        testWithdrawal(account, 300.0);  // Valid
        testWithdrawal(account, 2000.0); // Invalid (insufficient funds)
        testWithdrawal(account, -50.0);  // Invalid (negative)
    }
    
    private static void testDeposit(BankAccount account, double amount) {
        try {
            System.out.println("\nAttempting to deposit: $" + amount);
            account.deposit(amount);
        } catch (IllegalArgumentException e) {
            System.out.println("DEPOSIT ERROR: " + e.getMessage());
        } finally {
            System.out.println("Transaction complete. Current balance: $" + account.getBalance());
        }
    }
    
    private static void testWithdrawal(BankAccount account, double amount) {
        try {
            System.out.println("\nAttempting to withdraw: $" + amount);
            account.withdraw(amount);
        } catch (InsufficientFundsException e) {
            System.out.println("WITHDRAWAL ERROR: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("WITHDRAWAL ERROR: " + e.getMessage());
        } finally {
            System.out.println("Transaction complete. Current balance: $" + account.getBalance());
        }
    }
    
    // PART C: Employee Creation with Validation
    public static void partC_EmployeeCreation() {
        System.out.println("Testing employee creation with various scenarios:\n");
        
        // Test cases with explanations
        System.out.println("--- TEST CASE 1: Valid Employee ---");
        createEmployee("EMP01", "John Doe", "50000.0");
        
        System.out.println("\n--- TEST CASE 2: Invalid Employee Number (too long) ---");
        createEmployee("EMP12345678", "Jane Smith", "60000.0");
        
        System.out.println("\n--- TEST CASE 3: Invalid Employee Name (too short) ---");
        createEmployee("EMP02", "Jo", "45000.0");
        
        System.out.println("\n--- TEST CASE 4: Invalid Salary (negative) ---");
        createEmployee("EMP03", "Bob Wilson", "-5000.0");
        
        System.out.println("\n--- TEST CASE 5: Invalid Salary Format ---");
        createEmployee("EMP04", "Alice Brown", "not_a_number");
        
        // Interactive creation
        System.out.println("\n--- INTERACTIVE EMPLOYEE CREATION ---");
        createEmployeeInteractively();
    }
    
    private static void createEmployee(String empNumber, String empName, String salaryStr) {
        try {
            System.out.println("Creating employee with:");
            System.out.println("  Number: " + empNumber);
            System.out.println("  Name: " + empName);
            System.out.println("  Salary: " + salaryStr);
            
            double salary = Double.parseDouble(salaryStr);
            Employee employee = new Employee(empNumber, empName, salary);
            
            System.out.println("✅ SUCCESS: Employee created successfully!");
            System.out.println("Employee Details: " + employee);
            
        } catch (EmployeeDataException e) {
            System.out.println("❌ EMPLOYEE DATA ERROR: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ SALARY FORMAT ERROR: Invalid salary format");
        } catch (Exception e) {
            System.out.println("❌ UNEXPECTED ERROR: " + e.getMessage());
        }
    }
    
    private static void createEmployeeInteractively() {
        try {
            System.out.print("Enter employee number (max 5 characters): ");
            String empNumber = scanner.nextLine();
            
            System.out.print("Enter employee name (min 3 characters): ");
            String empName = scanner.nextLine();
            
            System.out.print("Enter net salary (positive number): ");
            String salaryInput = scanner.nextLine();
            
            // All validation in same try block
            double salary = Double.parseDouble(salaryInput);
            Employee employee = new Employee(empNumber, empName, salary);
            
            System.out.println("\n✅ SUCCESS: Employee created successfully!");
            System.out.println("Employee Details: " + employee);
            
        } catch (EmployeeDataException e) {
            System.out.println("❌ EMPLOYEE DATA ERROR: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ SALARY FORMAT ERROR: Please enter a valid number");
        } catch (Exception e) {
            System.out.println("❌ UNEXPECTED ERROR: " + e.getMessage());
        }
    }
}
```

## How to Run

1. **Save the code** to a file named `ExceptionHandlingTutorial.java`
2. **Compile** the program:

   ```bash
   javac ExceptionHandlingTutorial.java
   ```

3. **Run** the program:

   ```bash
   java ExceptionHandlingTutorial
   ```

## Expected Output

The program will demonstrate:

1. **Part A**: Interactive integer validation with retry on invalid input
2. **Part B**: Banking operations with various success/failure scenarios
3. **Part C**: Employee creation with comprehensive validation testing

## Key Concepts Summary

### 🔧 **Exception Handling Mechanisms**

- **Try-Catch-Finally**: Core exception handling structure
- **Throws Declaration**: Method-level exception specification
- **Custom Exceptions**: Domain-specific error handling

### 📊 **Exception Hierarchy**

```markdown
Exception (checked)
├── InsufficientFundsException (custom)
├── EmployeeDataException (custom)
└── RuntimeException (unchecked)
    ├── IllegalArgumentException
    └── NumberFormatException
```

### ✅ **Best Practices Demonstrated**

1. **Specific Exception Types**: Use appropriate exceptions for different scenarios
2. **Meaningful Messages**: Provide clear, actionable error messages
3. **Graceful Recovery**: Handle errors without crashing
4. **Resource Cleanup**: Use finally blocks for cleanup operations
5. **Input Validation**: Validate data at appropriate layers
6. **Exception Ordering**: Catch specific exceptions before general ones

### 🎯 **Real-World Applications**

- **User Input Validation**: Ensuring data integrity
- **Financial Systems**: Handling insufficient funds, invalid transactions
- **Data Processing**: Managing parsing errors, validation failures
- **API Development**: Providing meaningful error responses
- **System Integration**: Handling communication failures gracefully

This tutorial provides a solid foundation for understanding and implementing exception handling in Java applications!
