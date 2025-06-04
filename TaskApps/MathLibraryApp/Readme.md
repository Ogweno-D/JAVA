# Java MathLibrary and Calculator Application

## Table of Contents

1. [Project Overview](#project-overview)
2. [Project Structure](#project-structure)
3. [Source Code](#source-code)
4. [Building the Application](#building-the-application)
5. [JAR Types Explained](#jar-types-explained)
6. [Deployment Guide](#deployment-guide)
7. [Troubleshooting](#troubleshooting)
8. [Best Practices](#best-practices)

---

## Project Overview

This project demonstrates the creation of a modular Java application consisting of:

1. **MathLibrary** - A reusable library providing basic mathematical operations
2. **CalculatorApp** - A console-based calculator application that uses the MathLibrary

The project showcases:

- Library creation and packaging
- Dependency management without build tools (Maven/Gradle)
- Creating different types of JAR files (Thin vs Fat JAR)
- Proper Java packaging and deployment

### Key Features

- Basic arithmetic operations (addition, subtraction, multiplication, division)
- Interactive console interface
- Error handling and input validation
- Modular design with separation of concerns
- Multiple deployment options

---

## Project Structure

```project/
├── src/                          # Source code directory
│   └── com/
│       ├── mathlib/             # MathLibrary package
│       │   └── MathLibrary.java
│       └── calculator/          # Calculator application package
│           └── CalculatorApp.java
├── bin/                          # Compiled class files
│   └── com/
│       ├── mathlib/
│       │   └── MathLibrary.class
│       └── calculator/
│           └── CalculatorApp.class
├── lib/                          # Library JAR files
│   └── mathlib.jar
├── dist/                         # Distribution JAR files
│   ├── calculator-thin.jar
│   └── calculator-fat.jar
├── build/                        # Build artifacts (temporary)
├── build.bat                     # Windows build script
├── build.sh                      # Linux/Mac build script
└── README.md                     # Project documentation
```

---

## Source Code

### MathLibrary.java

**Package**: `com.mathlib`  
**Purpose**: Provides static methods for basic mathematical operations

#### Features

- **Addition**: `add(double a, double b)`
- **Subtraction**: `subtract(double a, double b)`
- **Multiplication**: `multiply(double a, double b)`
- **Division**: `divide(double a, double b)` with zero-division protection
- **Version Info**: `getVersion()` returns library version

#### Key Characteristics

- All methods are static (no instantiation required)
- Handles double precision floating-point numbers
- Includes proper error handling for division by zero
- Well-documented with Javadoc comments
- No main method (library only)

### CalculatorApp.java

**Package**: `com.calculator`  
**Purpose**: Interactive console calculator using MathLibrary

#### Main Features

- Menu-driven interface
- Input validation and error handling
- Continuous operation loop
- User-friendly prompts and output formatting
- Graceful exit option

#### Architecture

- **Main Method**: Entry point and application flow control
- **Menu System**: `displayMenu()`, `getOperationChoice()`
- **Input Handling**: `getOperands()` with validation
- **Calculation Engine**: `performCalculation()` using MathLibrary
- **Output Formatting**: `displayResult()` with proper formatting

---

## Building the Application

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Command line access (Terminal/Command Prompt)
- Basic understanding of Java compilation and JAR creation

### Manual Build Process

#### Step 1: Create Directory Structure

```bash
mkdir -p project/{src/com/{mathlib,calculator},bin,lib,dist}
cd project
```

#### Step 2: Compile MathLibrary

```bash
# Compile the library
javac -d bin src/com/mathlib/MathLibrary.java

# Create library JAR
jar cf lib/mathlib.jar -C bin com/mathlib
```

#### Step 3: Compile Calculator Application

```bash
# Compile with library dependency
javac -cp lib/mathlib.jar -d bin src/com/calculator/CalculatorApp.java
```

#### Step 4: Create Application JARs

**Thin JAR (Method 1 - Simple):**

```bash
# jar cfe dist/calculator-thin.jar com.calculator.CalculatorApp -C bin com/calculator
jar cfe CalculatorApp-Thin.jar com.calculator.CalculatorApp -C bin com

```

**Thin JAR (Method 2 - With Manifest):**

```bash
# Create custom manifest
cat > MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: com.calculator.CalculatorApp
Class-Path: lib/mathlib.jar

EOF

# Create JAR with manifest
jar cfm dist/calculator-thin.jar MANIFEST.MF -C bin com/calculator
```

**Fat JAR:**

```bash
# Create temporary directory
mkdir temp && cd temp

# Extract all classes
jar xf ../lib/mathlib.jar
jar xf ../dist/calculator-thin.jar

# Create fat JAR
jar cfe ../dist/calculator-fat.jar com.calculator.CalculatorApp .

# Cleanup
cd .. && rm -rf temp
```

### Automated Build Scripts

#### Windows (build.bat)

```batch
@echo off
echo Building MathLibrary and Calculator Application...

REM Create directories
mkdir bin lib dist 2>nul

REM Compile and package MathLibrary
javac -d bin src\com\mathlib\MathLibrary.java
jar cf lib\mathlib.jar -C bin com\mathlib

REM Compile Calculator App
javac -cp lib\mathlib.jar -d bin src\com\calculator\CalculatorApp.java

REM Create Thin JAR
jar cfe CalculatorThin.jar com.calculator.CalculatorApp -C bin com


REM Create Fat JAR
mkdir temp
cd temp
jar xf ..\lib\mathlib.jar
jar xf ..\dist\calculator-thin.jar
jar cfe ..\dist\calculator-fat.jar com.calculator.CalculatorApp .
cd .. && rmdir /s /q temp

echo Build completed successfully!
```

#### Linux/Mac (build.sh)

```bash
#!/bin/bash
echo "Building MathLibrary and Calculator Application..."

# Create directories
mkdir -p bin lib dist

# Compile and package MathLibrary
javac -d bin src/com/mathlib/MathLibrary.java
jar cf lib/mathlib.jar -C bin com/mathlib

# Compile Calculator App
javac -cp lib/mathlib.jar -d bin src/com/calculator/CalculatorApp.java

# Create Thin JAR
# jar cfe dist/calculator-thin.jar com.calculator.CalculatorApp -C bin com/calculator
jar cfe CalculatorThin.jar com.calculator.CalculatorApp -C bin com

# Create Fat JAR
mkdir temp && cd temp
jar xf ../lib/mathlib.jar
jar xf ../dist/calculator-thin.jar
jar cfe ../dist/calculator-fat.jar com.calculator.CalculatorApp .
cd .. && rm -rf temp

echo "Build completed successfully!"
```

---

## JAR Types Explained

### Thin JAR

**Definition**: Contains only the application classes, not dependencies.

**Characteristics:**

- **Size**: Small (only application classes)
- **Dependencies**: External (must be provided at runtime)
- **Portability**: Low (requires dependency management)
- **Use Case**: When dependencies are managed separately or shared across applications

**Running Thin JAR:**

- On `Linux/MacOs`:
  
```bash
java -cp CalculatorThin.jar:lib/MathLibrary.jar com.calculator.CalculatorApp
```

- On `Windows`
  
```bash
# Method 1: Explicit classpath
java -cp "CalculatorThin.jar;lib/MathLibrary.jar" com.calculator.CalculatorApp
```

```bash
# Method 2: With manifest Class-Path (if configured)
java -jar CalculatorApp-thin.jar
```

### More about Thin Jars

💡 Reminder of Key Points:

- Thin JAR doesn’t bundle dependencies, so we include both the app JAR and library JAR in the `-cp.com.calculator.CalculatorApp` must match your main class's package and class name.

- Make sure MathLibrary.jar is in the lib/ folder and readable.
- You cannot use -jar with thin JARs unless the dependency is shaded (which you're not doing here).

**Advantages:**

- Smaller file size
- Faster build times
- Dependency updates don't require rebuilding
- Suitable for environments with dependency management

**Disadvantages:**

- Complex deployment (multiple files)
- Classpath management required
- Runtime dependency resolution needed

### Fat JAR (Uber JAR)

**Definition**: Contains application classes and all dependencies in a single file.

**Characteristics:**

- **Size**: Large (includes all dependencies)
- **Dependencies**: Embedded (self-contained)
- **Portability**: High (single file deployment)
- **Use Case**: Standalone applications, simple deployment scenarios

**Running Fat JAR:**

```bash
java -jar CalculatorApp-Fat.jar
```

**Advantages:**

- Single file deployment
- No classpath management
- Self-contained and portable
- Simplified distribution

**Disadvantages:**

- Larger file size
- Duplicate dependencies in multiple applications
- Harder to update individual dependencies
- Potential licensing conflicts

### Comparison Table

| Aspect | Thin JAR | Fat JAR |
|--------|----------|---------|
| File Size | Small | Large |
| Dependencies | External | Embedded |
| Deployment | Complex | Simple |
| Runtime | Requires classpath | Self-contained |
| Updates | Individual components | Entire application |
| Memory Usage | Shared dependencies | Duplicated dependencies |
| Build Time | Fast | Slower |

---

## Deployment Guide

### Prerequisites for Target Environment

- Java Runtime Environment (JRE) 8 or higher
- Appropriate file system permissions
- Network access (if downloading dependencies)

### Deployment Scenarios

#### Scenario 1: Single User Desktop Application

**Recommended**: Fat JAR

```bash
# Copy single file
cp dist/calculator-fat.jar /usr/local/bin/calculator.jar

# Create launcher script
echo '#!/bin/bash' > /usr/local/bin/calculator
echo 'java -jar /usr/local/bin/calculator.jar "$@"' >> /usr/local/bin/calculator
chmod +x /usr/local/bin/calculator
```

#### Scenario 2: Server Environment with Multiple Applications

**Recommended**: Thin JAR with shared libraries

```bash
# Application structure
/opt/myapp/
├── lib/
│   └── mathlib.jar
├── apps/
│   ├── calculator-thin.jar
│   └── other-app-thin.jar
└── bin/
    └── calculator.sh
```

#### Scenario 3: Container Deployment (Docker)

```dockerfile
FROM openjdk:11-jre-slim
COPY dist/calculator-fat.jar /app/calculator.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "calculator.jar"]
```

### Installation Scripts

#### Windows Installation (install.bat)

```batch
@echo off
echo Installing Calculator Application...

REM Create application directory
mkdir "C:\Program Files\Calculator" 2>nul

REM Copy files
copy dist\calculator-fat.jar "C:\Program Files\Calculator\"

REM Create batch launcher
echo @echo off > "C:\Program Files\Calculator\calculator.bat"
echo java -jar "C:\Program Files\Calculator\calculator-fat.jar" >> "C:\Program Files\Calculator\calculator.bat"

echo Installation completed!
echo Run: "C:\Program Files\Calculator\calculator.bat"
```

#### Linux Installation (install.sh)

```bash
#!/bin/bash
echo "Installing Calculator Application..."

# Create application directory
sudo mkdir -p /opt/calculator

# Copy files
sudo cp dist/calculator-fat.jar /opt/calculator/

# Create launcher script
sudo tee /opt/calculator/calculator.sh > /dev/null << EOF
#!/bin/bash
java -jar /opt/calculator/calculator-fat.jar "\$@"
EOF

# Make executable
sudo chmod +x /opt/calculator/calculator.sh

# Create system-wide link
sudo ln -sf /opt/calculator/calculator.sh /usr/local/bin/calculator

echo "Installation completed!"
echo "Run: calculator"
```

---

## Troubleshooting

### Common Issues and Solutions

#### 1. ClassNotFoundException

**Error**: `java.lang.ClassNotFoundException: com.mathlib.MathLibrary`

**Cause**: MathLibrary not in classpath

**Solutions**:

```bash
# Fix classpath (Linux/Mac)
java -cp "calculator-thin.jar:lib/mathlib.jar" com.calculator.CalculatorApp

# Fix classpath (Windows)
java -cp "calculator-thin.jar;lib/mathlib.jar" com.calculator.CalculatorApp

# Or use fat JAR
java -jar calculator-fat.jar
```

#### 2. No Main Manifest Attribute

**Error**: `no main manifest attribute, in calculator-thin.jar`

**Cause**: JAR created without main class specification

**Solutions**:

```bash
# Method 1: Recreate with main class
jar cfe calculator-thin.jar com.calculator.CalculatorApp -C bin com/calculator

# Method 2: Add manifest to existing JAR
echo "Main-Class: com.calculator.CalculatorApp" > temp-manifest.mf
jar ufm calculator-thin.jar temp-manifest.mf
```

#### 3. Could Not Find or Load Main Class

**Error**: `Error: Could not find or load main class com.calculator.CalculatorApp`

**Cause**: Incorrect main class name or missing class file

**Diagnosis**:

```bash
# Check JAR contents
jar tf CalculatorApp-thin.jar

# Expected output should include:
# com/calculator/CalculatorApp.class
```

**Solutions**:

- Verify correct package structure
- Ensure class file exists in JAR
- Check for typos in class name

#### 4. Compilation Errors

**Error**: Various compilation errors

**Common Issues**:

- Missing import statements
- Incorrect package declarations
- Java version compatibility

**Solutions**:

```bash
# Check Java version
java -version
javac -version

# Compile with verbose output
javac -verbose -cp lib/mathlib.jar -d bin src/com/calculator/CalculatorApp.java
```

#### 5. Permission Denied

**Error**: Permission denied when running scripts

**Solution**:

```bash
# Make script executable
chmod +x build.sh
chmod +x install.sh
```

### Debugging Commands

#### Verify JAR Contents

```bash
# List files in JAR
jar tf calculator-thin.jar

# Extract and examine
mkdir debug && cd debug
jar xf ../calculator-thin.jar
find . -name "*.class"
cd .. && rm -rf debug
```

#### Check Manifest

```bash
# Extract manifest
jar xf calculator-thin.jar META-INF/MANIFEST.MF
cat META-INF/MANIFEST.MF
```

#### Test Classpath

```bash
# Run with verbose class loading
java -verbose:class -cp "calculator-thin.jar:lib/mathlib.jar" com.calculator.CalculatorApp
```

#### Verify Dependencies

```bash
# Check if MathLibrary methods are accessible
javap -cp lib/mathlib.jar com.mathlib.MathLibrary
```

---

## Best Practices

### Code Organization

#### 1. Package Structure

- Use meaningful package names
- Follow reverse domain naming convention
- Separate library and application code
- Maintain consistent naming patterns

#### 2. Class Design

```java
// Good: Static utility class
public class MathLibrary {
    private MathLibrary() {} // Prevent instantiation
    
    public static double add(double a, double b) {
        return a + b;
    }
}

// Good: Application class with clear separation
public class CalculatorApp {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Main application logic
    }
    
    private static void displayMenu() {
        // UI logic
    }
}
```

#### 3. Error Handling

```java
// Good: Proper exception handling
public static double divide(double a, double b) {
    if (b == 0) {
        throw new ArithmeticException("Division by zero is not allowed");
    }
    return a / b;
}

// Good: User input validation
try {
    int choice = scanner.nextInt();
    scanner.nextLine(); // consume newline
} catch (InputMismatchException e) {
    System.err.println("Invalid input. Please enter a number.");
    scanner.nextLine(); // clear invalid input
}
```

### Build and Deployment

#### 1. Build Scripts

- Include error checking in build scripts
- Provide clear success/failure messages
- Clean up temporary files
- Support both Windows and Unix systems

#### 2. JAR Creation

```bash
# Good: Include version in JAR name
jar cfe calculator-v1.0-thin.jar com.calculator.CalculatorApp -C bin com/calculator

# Good: Meaningful manifest entries
Manifest-Version: 1.0
Implementation-Title: Mathematical Calculator
Implementation-Version: 1.0
Implementation-Vendor: Your Organization
Main-Class: com.calculator.CalculatorApp
Class-Path: lib/mathlib-1.0.jar
```

#### 3. Documentation

- Include README with build instructions
- Document all command-line options
- Provide troubleshooting guide
- Include version information

### Performance and Maintenance

#### 1. Memory Management

```java
// Good: Close resources
try (Scanner scanner = new Scanner(System.in)) {
    // Use scanner
} // Automatically closed

// Good: Reuse objects where possible
private static final DecimalFormat formatter = new DecimalFormat("#.##");
```

#### 2. Version Management

- Use semantic versioning (MAJOR.MINOR.PATCH)
- Include version in library classes
- Tag releases in version control
- Maintain compatibility matrices

#### 3. Testing Strategy

```java
// Good: Unit tests for library functions
@Test
public void testDivisionByZero() {
    assertThrows(ArithmeticException.class, () -> {
        MathLibrary.divide(10, 0);
    });
}

// Good: Integration tests for application flow
@Test
public void testCalculatorFlow() {
    // Test complete user interaction
}
```

### Security Considerations

#### 1. Input Validation

- Validate all user inputs
- Handle edge cases (infinity, NaN)
- Prevent buffer overflow attacks
- Sanitize file paths if applicable

#### 2. JAR Security

- Sign JARs for distribution
- Verify JAR integrity
- Use secure communication for downloads
- Implement proper access controls

#### 3. Dependency Management

- Keep dependencies updated
- Scan for known vulnerabilities
- Use dependency locks
- Monitor security advisories

---

## Conclusion

This documentation provides a comprehensive guide for building, deploying, and maintaining the Java MathLibrary and Calculator application. The modular design allows for easy extension and maintenance, while the multiple JAR deployment options provide flexibility for different environments.

Key takeaways:

- **Thin JARs** are ideal for managed environments with shared dependencies
- **Fat JARs** simplify deployment for standalone applications
<!-- - Proper build automation reduces errors and improves consistency
- Comprehensive documentation and troubleshooting guides improve maintainability -->

For additional support or contributions, please refer to the project repository or contact the development team.

## Contributor

Dennis Ogweno
