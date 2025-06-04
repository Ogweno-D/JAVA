# Java Object-Oriented Programming (OOP) Concepts

## Overview

Object-Oriented Programming is a programming paradigm based on the concept of "objects" which contain data (attributes) and code (methods). Java is a pure OOP language that implements four main principles.

---

## 1. Encapsulation 🔒

**Definition**: Bundling data and methods together while hiding internal implementation details.

**Key Points**:

- Use `private` access modifier for data members
- Provide `public` getter and setter methods
- Control access to data
- Data validation and protection

**Example**:

```java
class Person {
    private String name;    // Hidden data
    private int age;        // Hidden data
    
    // Public method to access private data
    public String getName() {
        return name;
    }
    
    // Public method to modify private data with validation
    public void setAge(int age) {
        if (age > 0 && age < 150) {
            this.age = age;
        }
    }
}
```

**Benefits**:

- Data security
- Easy maintenance
- Flexible code

---

## 2. Inheritance 👨‍👩‍👧‍👦

**Definition**: Creating new classes based on existing classes, inheriting properties and methods.

**Key Points**:

- Use `extends` keyword
- Parent class (superclass) → Child class (subclass)
- IS-A relationship
- Code reusability

**Example**:

```java
// Parent class
class Person {
    protected String name;
    protected int age;
    
    public void introduce() {
        System.out.println("Hi, I'm " + name);
    }
}

// Child class
class Student extends Person {
    private String studentId;
    
    // Inherits name, age, and introduce() method
    // Can add new methods
    public void study() {
        System.out.println(name + " is studying");
    }
}

// Grandchild class
class PrimaryStudent extends Student {
    private int grade;
    
    public void playAtRecess() {
        System.out.println(name + " is playing!");
    }
}
```

**Benefits**:

- Code reusability
- Method overriding
- Hierarchical classification

---

## 3. Polymorphism 🎭

**Definition**: "Many forms" - same method name behaves differently in different classes.

**Types**:

1. **Runtime Polymorphism** (Method Overriding)
2. **Compile-time Polymorphism** (Method Overloading)

### Method Overriding (Runtime Polymorphism)

```java
class Person {
    public void introduce() {
        System.out.println("I'm a person");
    }
}

class Student extends Person {
    @Override
    public void introduce() {
        System.out.println("I'm a student");
    }
}

class PrimaryStudent extends Student {
    @Override
    public void introduce() {
        System.out.println("I'm a primary student");
    }
}

// Usage
Person[] people = {new Person(), new Student(), new PrimaryStudent()};
for (Person p : people) {
    p.introduce(); // Different output for each!
}
```

### Method Overloading (Compile-time Polymorphism)

```java
class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

**Benefits**:

- Flexibility
- Code reusability
- Easy maintenance

---

## 4. Abstraction 🎨

**Definition**: Hiding implementation details and showing only essential features.

**Types**:

1. **Abstract Classes** (Partial Abstraction)
2. **Interfaces** (Complete Abstraction)

### Abstract Classes

```java
abstract class Vehicle {
    protected String brand;
    
    // Abstract method - must be implemented by child classes
    public abstract void start();
    
    // Concrete method - inherited by all child classes
    public void displayInfo() {
        System.out.println("Brand: " + brand);
    }
}

class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started!");
    }
}

class Tesla extends Car {
    @Override
    public void start() {
        System.out.println("Tesla is ready - silent start!");
    }
}
```

### Interfaces

```java
interface Driveable {
    void drive();    // Abstract method
    void brake();    // Abstract method
}

interface Chargeable {
    void charge();
    double getBatteryLevel();
}

// Multiple inheritance through interfaces
class ElectricCar implements Driveable, Chargeable {
    public void drive() {
        System.out.println("Driving silently...");
    }
    
    public void brake() {
        System.out.println("Regenerative braking...");
    }
    
    public void charge() {
        System.out.println("Charging battery...");
    }
    
    public double getBatteryLevel() {
        return 85.0;
    }
}
```

**Benefits**:

- Code organization
- Multiple inheritance (interfaces)
- Loose coupling

---

## 5. Additional Important Concepts

### Static Members

```java
class University {
    private static int totalStudents = 0;  // Class variable
    private String studentName;            // Instance variable
    
    public University(String name) {
        this.studentName = name;
        totalStudents++;  // Shared among all objects
    }
    
    public static int getTotalStudents() {  // Class method
        return totalStudents;
    }
}

// Usage
University.getTotalStudents();  // Called without creating object
```

### Final Keyword

```java
// Final class - cannot be extended
final class MathUtils {
    // Final variable - constant
    public static final double PI = 3.14159;
    
    // Final method - cannot be overridden
    public final void calculate() {
        System.out.println("Calculating...");
    }
}
```

### Access Modifiers

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `default` | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

---

## 6. Key Relationships

### IS-A Relationship (Inheritance)

```java
class Animal { }
class Dog extends Animal { }
// Dog IS-A Animal ✅
```

### HAS-A Relationship (Composition)

```java
class Engine { }
class Car {
    private Engine engine;  // Car HAS-A Engine
}
```

---

## 7. Benefits of OOP

| Benefit | Description |
|---------|-------------|
| **Modularity** | Code is organized into separate classes |
| **Reusability** | Classes can be reused in different programs |
| **Maintainability** | Easy to modify and update code |
| **Scalability** | Easy to add new features |
| **Security** | Data hiding through encapsulation |
| **Flexibility** | Polymorphism allows flexible code |

---

## 8. Quick Reference

### Creating Objects

```java
// Class definition
class Person {
    private String name;
    
    public Person(String name) {  // Constructor
        this.name = name;
    }
}

// Object creation
Person person = new Person("John");
```

### Method Overriding Rules

- Use `@Override` annotation
- Same method signature as parent
- Cannot reduce visibility
- Cannot override static/final methods

### Interface vs Abstract Class

| Feature | Interface | Abstract Class |
|---------|-----------|----------------|
| Methods | All abstract (default) | Both abstract & concrete |
| Variables | public, static, final | Any type |
| Inheritance | Multiple | Single |
| Constructor | No | Yes |

---

## 9. Common Interview Questions

1. **What are the four pillars of OOP?**
   - Encapsulation, Inheritance, Polymorphism, Abstraction

2. **Difference between method overloading and overriding?**
   - Overloading: Same name, different parameters (compile-time)
   - Overriding: Same signature, different implementation (runtime)

3. **Can we override static methods?**
   - No, static methods belong to class, not instance

4. **What is the difference between abstract class and interface?**
   - Abstract class: Partial abstraction, single inheritance
   - Interface: Complete abstraction, multiple inheritance

5. **What is super keyword?**
   - Refers to parent class object, used to call parent constructor/methods

---

## Conclusion

OOP makes code more organized, reusable, and maintainable. The four main principles work together to create robust software systems. Practice these concepts with real-world examples to master Java programming! 🚀
