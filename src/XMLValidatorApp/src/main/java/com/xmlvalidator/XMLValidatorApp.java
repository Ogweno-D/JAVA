package com.xmlvalidator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XMLValidatorApp {
    
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
        
        // Print current date and time with timezone
        LocalDateTime now = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("Current Date/Time: " + now.format(formatter) + " " + currentZone);
        
        // Print timezone information
        String timezone = System.getProperty("user.timezone");
        if (timezone != null) {
            System.out.println("JVM Timezone Setting: " + timezone);
        } else {
            System.out.println("Default System Timezone: " + TimeZone.getDefault().getID());
        }
        
        // Print heap information
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("Max Heap Size: " + formatBytes(maxMemory));
        System.out.println("Current Heap Size: " + formatBytes(totalMemory));
        System.out.println("Used Memory: " + formatBytes(usedMemory));
        System.out.println("Free Memory: " + formatBytes(freeMemory));
        System.out.println();
    }
    
    private static String formatBytes(long bytes) {
        if (bytes == Long.MAX_VALUE) {
            return "No limit";
        }
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024));
        return String.format("%.1f GB", bytes / (1024.0 * 1024 * 1024));
    }
    
    private static CommandLineArgs parseArguments(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: No arguments provided.");
            return null;
        }
        
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
                case XML_ARG -> xmlFilePath = value;
                case XSD_ARG -> xsdFilePath = value;
                default -> {
                    System.err.println("Error: Unknown argument '" + arg + "'. Expected " + XML_ARG + " or " + XSD_ARG);
                    return null;
                }
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
        System.out.println("=== XML Validation Process ===");
        System.out.println("XML File: " + xmlFilePath);
        System.out.println("XSD File: " + xsdFilePath);
        System.out.println();
        
        try {
            // Check if files exist and are readable
            File xmlFile = new File(xmlFilePath);
            File xsdFile = new File(xsdFilePath);
            
            if (!xmlFile.exists()) {
                System.err.println("Error: XML file does not exist: " + xmlFilePath);
                return;
            }
            
            if (!xmlFile.canRead()) {
                System.err.println("Error: Cannot read XML file: " + xmlFilePath);
                return;
            }
            
            if (!xsdFile.exists()) {
                System.err.println("Error: XSD file does not exist: " + xsdFilePath);
                return;
            }
            
            if (!xsdFile.canRead()) {
                System.err.println("Error: Cannot read XSD file: " + xsdFilePath);
                return;
            }
            
            System.out.println("Files exist and are readable");
            System.out.println("Starting validation...");
            System.out.println();
            
            // Create schema factory
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
            // Load XSD schema
            System.out.println("Loading XSD schema...");
            Schema schema = schemaFactory.newSchema(xsdFile);
            System.out.println("✓ XSD schema loaded successfully");
            
            // Create validator
            Validator validator = schema.newValidator();
            
            // Set error handler for detailed error reporting
            XMLValidationErrorHandler errorHandler = new XMLValidationErrorHandler();
            validator.setErrorHandler(errorHandler);
            
            // Validate XML
            System.out.println("Validating XML against schema...");
            validator.validate(new StreamSource(xmlFile));
            
            // Report results
            System.out.println();
            if (errorHandler.hasErrors()) {
                System.out.println("XML VALIDATION FAILED");
                System.out.println("Total errors found: " + errorHandler.getErrors().size());
                System.out.println();
                System.out.println("Validation Errors:");
                errorHandler.getErrors().forEach(error -> System.out.println("  • " + error));
                
                if (!errorHandler.getWarnings().isEmpty()) {
                    System.out.println();
                    System.out.println("Warnings:");
                    errorHandler.getWarnings().forEach(warning -> System.out.println("  • " + warning));
                }
            } else {
                System.out.println("XML VALIDATION SUCCESSFUL");
                System.out.println("The XML file is valid according to the provided XSD schema.");
                
                if (!errorHandler.getWarnings().isEmpty()) {
                    System.out.println();
                    System.out.println("Warnings (validation still successful):");
                    errorHandler.getWarnings().forEach(warning -> System.out.println("  • " + warning));
                }
            }
            
        } catch (IOException | SAXException e) {
            System.err.println(" XML VALIDATION FAILED");
            System.err.println("Unexpected error during validation: " + e.getMessage());
            
            // Print more detailed error information in debug mode
            if (Boolean.getBoolean("debug")) {
                System.err.println("Full stack trace:");
            } else {
                System.err.println("(Run with -Ddebug=true for detailed error information)");
            }
        }
    }
    
    private static void printUsage() {
        System.out.println();
        System.out.println("USAGE:");
        System.out.println("  java [JVM_OPTIONS] -jar XMLValidator.jar -xml <xml_file_path> -xsd <xsd_file_path>");
        System.out.println();
        System.out.println("REQUIRED ARGUMENTS:");
        System.out.println("  -xml <path>    Path to the XML file to validate");
        System.out.println("  -xsd <path>    Path to the XSD schema file");
        System.out.println();
        System.out.println("EXAMPLE:");
        System.out.println("  java -jar XMLValidator.jar -xml data.xml -xsd schema.xsd");
        System.out.println();
        System.out.println("EXAMPLE WITH JVM OPTIONS:");
        System.out.println("  java -Duser.timezone=UTC -Xms256m -Xmx1g -jar XMLValidator.jar -xml data.xml -xsd schema.xsd");
        System.out.println();
        System.out.println("COMMON JVM OPTIONS:");
        System.out.println("  -Duser.timezone=<tz>   Set timezone (UTC, America/New_York, Europe/London, etc.)");
        System.out.println("  -Xms<size>             Set minimum heap size (256m, 512m, 1g, etc.)");
        System.out.println("  -Xmx<size>             Set maximum heap size (512m, 1g, 2g, etc.)");
        System.out.println("  -Ddebug=true           Enable detailed error reporting");
        System.out.println();
    }
    
    // Inner class for command-line arguments
    private static class CommandLineArgs {
        final String xmlFilePath;
        final String xsdFilePath;
        
        CommandLineArgs(String xmlFilePath, String xsdFilePath) {
            this.xmlFilePath = xmlFilePath;
            this.xsdFilePath = xsdFilePath;
        }
        
        @Override
        public String toString() {
            return String.format("CommandLineArgs{xmlFilePath='%s', xsdFilePath='%s'}", 
                               xmlFilePath, xsdFilePath);
        }
    }
}