# Java XML Validator Application

## Table of Contents

1. [Project Overview](#project-overview)
2. [JVM Options Explained](#jvm-options-explained)
3. [Java Application Code](#java-application-code)
4. [Command-Line Arguments Handling](#command-line-arguments-handling)
5. [XML Validation Implementation](#xml-validation-implementation)
6. [Building and Running](#building-and-running)
7. [Complete Example](#complete-example)

## Project Overview

We'll create a Java application that:

- Accepts JVM options for timezone and heap sizes
- Takes command-line arguments for XML and XSD file paths
- Validates XML against XSD schema
- Prints current date/time
- Handles errors gracefully
- Compiles into a runnable JAR

## JVM Options Explained

### Key JVM Options We'll Use

1. **Timezone**: `-Duser.timezone=UTC`
   - Sets the default timezone for the application
   - Format: `-Duser.timezone=<timezone_id>`

2. **Minimum Heap Size**: `-Xms<size>`
   - Sets initial heap size
   - Example: `-Xms512m` (512 MB)

3. **Maximum Heap Size**: `-Xmx<size>`
   - Sets maximum heap size
   - Example: `-Xmx2g` (2 GB)

### Complete JVM Command Example

```bash
java -Duser.timezone=America/New_York -Xms256m -Xmx1g -jar XMLValidatorApp.jar -xml test.xml -xsd schema.xsd
```

## Java Application Code

### Main Application Class

```java
package com.xmlvalidator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class XMLValidatorAppApp {
    
    private static final String XML_ARG = "-xml";
    private static final String XSD_ARG = "-xsd";
    
    public static void main(String[] args) {
        // Print JVM information and current date/time
        printSystemInfo();
        
        // Parse command-line arguments
        CommandLineArgs cmdArgs = parseArguments(args);
        
        if (cmdArgs == null) {
            printUsage();
            System.exit(1);
        }
        
        // Validate XML
        validateXML(cmdArgs.xmlFilePath, cmdArgs.xsdFilePath);
    }
    
    private static void printSystemInfo() {
        System.out.println("=== XML Validator Application ===");
        System.out.println();
        
        // Print current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        System.out.println("Current Date/Time: " + now.format(formatter));
        
        // Print timezone information
        String timezone = System.getProperty("user.timezone");
        if (timezone != null) {
            System.out.println("JVM Timezone: " + timezone);
        } else {
            System.out.println("Default Timezone: " + TimeZone.getDefault().getID());
        }
        
        // Print heap information
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        
        System.out.println("Max Heap Size: " + formatBytes(maxMemory));
        System.out.println("Current Heap Size: " + formatBytes(totalMemory));
        System.out.println();
    }
    
    private static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024));
        return String.format("%.1f GB", bytes / (1024.0 * 1024 * 1024));
    }
    
    private static CommandLineArgs parseArguments(String[] args) {
        if (args.length != 4) {
            System.err.println("Error: Invalid number of arguments. Expected 4, got " + args.length);
            return null;
        }
        
        String xmlFilePath = null;
        String xsdFilePath = null;
        
        // Parse arguments
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 >= args.length) {
                System.err.println("Error: Missing value for argument " + args[i]);
                return null;
            }
            
            String arg = args[i];
            String value = args[i + 1];
            
            switch (arg) {
                case XML_ARG:
                    xmlFilePath = value;
                    break;
                case XSD_ARG:
                    xsdFilePath = value;
                    break;
                default:
                    System.err.println("Error: Unknown argument " + arg);
                    return null;
            }
        }
        
        // Validate that both arguments were provided
        if (xmlFilePath == null) {
            System.err.println("Error: Missing required argument " + XML_ARG);
            return null;
        }
        
        if (xsdFilePath == null) {
            System.err.println("Error: Missing required argument " + XSD_ARG);
            return null;
        }
        
        return new CommandLineArgs(xmlFilePath, xsdFilePath);
    }
    
    private static void validateXML(String xmlFilePath, String xsdFilePath) {
        System.out.println("=== XML Validation ===");
        System.out.println("XML File: " + xmlFilePath);
        System.out.println("XSD File: " + xsdFilePath);
        System.out.println();
        
        try {
            // Check if files exist
            File xmlFile = new File(xmlFilePath);
            File xsdFile = new File(xsdFilePath);
            
            if (!xmlFile.exists()) {
                System.err.println("Error: XML file does not exist: " + xmlFilePath);
                return;
            }
            
            if (!xsdFile.exists()) {
                System.err.println("Error: XSD file does not exist: " + xsdFilePath);
                return;
            }
            
            // Create schema factory
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
            // Load XSD schema
            Schema schema = schemaFactory.newSchema(xsdFile);
            
            // Create validator
            Validator validator = schema.newValidator();
            
            // Set error handler for detailed error reporting
            XMLValidationErrorHandler errorHandler = new XMLValidationErrorHandler();
            validator.setErrorHandler(errorHandler);
            
            // Validate XML
            validator.validate(new StreamSource(xmlFile));
            
            // Report results
            if (errorHandler.hasErrors()) {
                System.out.println("XML Validation FAILED");
                System.out.println("Errors found:");
                errorHandler.getErrors().forEach(error -> System.out.println("  - " + error));
            } else {
                System.out.println("XML Validation SUCCESSFUL");
                System.out.println("The XML file is valid according to the provided XSD schema.");
            }
            
        } catch (Exception e) {
            System.err.println("XML Validation FAILED");
            System.err.println("Error during validation: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void printUsage() {
        System.out.println("Usage: java -jar XMLValidatorApp.jar -xml <xml_file_path> -xsd <xsd_file_path>");
        System.out.println();
        System.out.println("Example:");
        System.out.println("java -Duser.timezone=UTC -Xms256m -Xmx1g -jar XMLValidatorApp.jar -xml data.xml -xsd schema.xsd");
        System.out.println();
        System.out.println("JVM Options:");
        System.out.println("  -Duser.timezone=<timezone>  Set timezone (e.g., UTC, America/New_York)");
        System.out.println("  -Xms<size>                  Set minimum heap size (e.g., 256m, 1g)");
        System.out.println("  -Xmx<size>                  Set maximum heap size (e.g., 512m, 2g)");
    }
    
    // Inner class for command-line arguments
    private static class CommandLineArgs {
        final String xmlFilePath;
        final String xsdFilePath;
        
        CommandLineArgs(String xmlFilePath, String xsdFilePath) {
            this.xmlFilePath = xmlFilePath;
            this.xsdFilePath = xsdFilePath;
        }
    }
}
```

### Error Handler Class

```java
package com.xmlvalidator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.util.ArrayList;
import java.util.List;

public class XMLValidationErrorHandler implements ErrorHandler {
    
    private final List<String> errors = new ArrayList<>();
    private final List<String> warnings = new ArrayList<>();
    
    @Override
    public void warning(SAXParseException e) throws SAXException {
        String warning = String.format("Warning at line %d, column %d: %s", 
            e.getLineNumber(), e.getColumnNumber(), e.getMessage());
        warnings.add(warning);
        System.out.println("⚠️  " + warning);
    }
    
    @Override
    public void error(SAXParseException e) throws SAXException {
        String error = String.format("Error at line %d, column %d: %s", 
            e.getLineNumber(), e.getColumnNumber(), e.getMessage());
        errors.add(error);
    }
    
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        String error = String.format("Fatal error at line %d, column %d: %s", 
            e.getLineNumber(), e.getColumnNumber(), e.getMessage());
        errors.add(error);
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
    
    public List<String> getWarnings() {
        return new ArrayList<>(warnings);
    }
}
```

## Command-Line Arguments Handling

### Key Features

1. **Argument Parsing**: Handles `-xml` and `-xsd` flags
2. **Validation**: Ensures both arguments are provided
3. **Error Handling**: Graceful handling of missing/invalid arguments
4. **File Existence**: Checks if specified files exist

### Supported Format

```bash
cd build

java -jar XMLValidatorApp.jar -xml <path_to_xml> -xsd <path_to_xsd>

```

## XML Validation Implementation

### Components

1. **SchemaFactory**: Creates XML Schema objects
2. **Validator**: Validates XML against XSD
3. **ErrorHandler**: Captures and reports validation errors
4. **File Validation**: Ensures files exist before processing

### Error Handling

- File not found errors
- Schema parsing errors  
- XML validation errors
- Detailed error reporting with line numbers

## Building and Running

### Project Structure

```bash
XMLValidatorApp/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── XMLValidatorApp/
│                   ├── XMLValidatorAppApp.java
│                   └── XMLValidationErrorHandler.java
├── test-files/
│   ├── book/
│   |        ├── book.xml
│   |        └── book.xsd
|   |      
│   └── student/
|             ├── student.xml
│             └── student.xsd
└── build/
```

### Compilation Steps

1. **Create Directory Structure**:

```bash
mkdir -p XMLValidatorApp/src/main/java/com/XMLValidatorApp
mkdir -p XMLValidatorApp/test-files
mkdir -p XMLValidatorApp/build
```

2. **Compile Java Files**:

```bash
cd XMLValidatorApp
javac -d build src/main/java/com/XMLValidatorApp/*.java
```

3. **Create Manifest File** (`build/MANIFEST.MF`):

```
Manifest-Version: 1.0
Main-Class: com.XMLValidatorApp.XMLValidatorAppApp

```

`Remember to add a new line in the manifest.mf file`

4. **Create JAR File**:

```bash
cd build
jar cfm XMLValidatorApp.jar MANIFEST.MF com/
```

### Running the Application

```bash
# Basic run
java -jar XMLValidatorApp.jar -xml sample.xml -xsd sample.xsd

# With JVM options
java -Duser.timezone=UTC -Xms256m -Xmx1g -jar XMLValidatorApp.jar -xml sample.xml -xsd sample.xsd

# With different timezone
java -Duser.timezone=America/New_York -Xms512m -Xmx2g -jar XMLValidatorApp.jar -xml data.xml -xsd schema.xsd
```

## Complete Example

### Sample XML File (`test-files/book/book.xml`)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<book xmlns="http://example.com/books">
    <title>Java Programming</title>
    <author>John Doe</author>
    <year>2023</year>
    <price>29.99</price>
</book>
```

### Sample XSD File (`test-files/book/book.xsd`)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://example.com/books"
           xmlns="http://example.com/books"
           elementFormDefault="qualified">
    
    <xs:element name="book">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="title" type="xs:string"/>
                <xs:element name="author" type="xs:string"/>
                <xs:element name="year" type="xs:int"/>
                <xs:element name="price" type="xs:decimal"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
```

### Build Script (`build.sh`)

```bash
#!/bin/bash

echo "Building XML Validator Application..."

# Clean previous build
rm -rf build/*

# Compile Java files
echo "Compiling Java files..."
javac -d build src/main/java/com/XMLValidatorApp/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    
    # Create manifest
    echo "Creating manifest..."
    cat > build/MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: com.XMLValidatorApp.XMLValidatorAppApp

EOF
    
    # Create JAR
    echo "Creating JAR file..."
    cd build
    jar cfm XMLValidatorApp.jar MANIFEST.MF com/
    
    if [ $? -eq 0 ]; then
        echo "JAR created successfully: build/XMLValidatorApp.jar"
        echo "Usage: java -jar build/XMLValidatorApp.jar -xml <xml_file> -xsd <xsd_file>"
    else
        echo "Failed to create JAR file"
        exit 1
    fi
else
    echo "Compilation failed"
    exit 1
fi
```

### Test Script (`test.sh`)

```bash
#!/bin/bash

echo "Testing XML Validator Application..."

# Test with valid XML
echo "Test 1: Valid XML"
java -Duser.timezone=UTC -Xms256m -Xmx1g -jar build/XMLValidatorApp.jar -xml test-files/book.xml -xsd test-files/book.xsd

echo ""
echo "----------------------------------------"
echo ""

# Test with missing file
echo "Test 2: Missing XML file"
java -jar build/XMLValidatorApp.jar -xml nonexistent.xml -xsd test-files/book.xsd

echo ""
echo "----------------------------------------"
echo ""

# Test with wrong arguments
echo "Test 3: Invalid arguments"
java -jar build/XMLValidatorApp.jar -wrong argument
```

## Key Learning Points

1. **JVM Options**: Understanding heap management and system properties
2. **Command-Line Parsing**: Robust argument handling with validation
3. **XML Processing**: Using Java's built-in XML validation APIs
4. **Error Handling**: Graceful error management and user feedback
5. **JAR Creation**: Building distributable Java applications
6. **Date/Time Handling**: Working with timezones and formatting

## Best Practices Demonstrated

1. **Single Responsibility**: Each method has a clear purpose
2. **Error Handling**: Comprehensive error checking and reporting
3. **User Experience**: Clear output and helpful error messages
4. **Resource Management**: Proper handling of file resources
5. **Documentation**: Well-commented code with usage examples

This application provides a complete solution for XML validation with proper JVM option handling, robust error management, and professional-grade code structure.

## Contributor

Dennis Ogweno
