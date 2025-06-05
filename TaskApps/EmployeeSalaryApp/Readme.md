# DAY 3

I have learnt OOP!

## Employee Salary App

This Java project is a simple console application for managing and calculating employee salaries. It demonstrates object-oriented programming concepts such as inheritance, encapsulation, and polymorphism and abstraction.

### Features

* Employee Management:
Supports both full-time and part-time employees.

* Salary Calculation:
Calculates salaries based on employee type and relevant parameters.

* Extensible Design:
Easily add new employee types by extending the base Employee class.

Project Structure

```bash
EmployeeSalaryApp/
└── src/
    └── main/
        └── java/
            └── com/
                └── employee_salary/
                    ├── Employee.java
                    ├── FullTimeEmployee.java
                    ├── PartTimeEmployee.java
                    └── Main.java
```

How to Run
Compile the project:

```bash
javac -d build src/main/java/com/employee_salary/*.java
```

Run the application:

```bash

java -cp build com.employee_salary.Main
```

#### Requirements

* Java 8 or higher

* Command line or IDE (e.g., VS Code, IntelliJ IDEA)

#### Customization

> You can add more employee types by creating new classes that extend Employee and implementing the required methods.

#### License

This project is for educational purposes. No specific license is applied.
