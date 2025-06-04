package com.xmlvalidator;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Custom error handler for XML validation that captures and categorizes
 * validation errors, warnings, and fatal errors.
 */
public class XMLValidationErrorHandler implements ErrorHandler {
    
    private final List<String> errors = new ArrayList<>();
    private final List<String> warnings = new ArrayList<>();
    private final List<String> fatalErrors = new ArrayList<>();
    
    /**
     * Handle warning conditions during XML validation.
     * Warnings don't prevent successful validation but indicate potential issues.
     */
    @Override
    public void warning(SAXParseException e) throws SAXException {
        String warning = formatErrorMessage("WARNING", e);
        warnings.add(warning);
        
        // Print warning immediately for real-time feedback
        System.out.println("Relaax! This is just a warning \n " + warning);
    }
    
    /**
     * Handle recoverable errors during XML validation.
     * These are violations of the schema but parsing can continue.
     */
    @Override
    public void error(SAXParseException e) throws SAXException {
        String error = formatErrorMessage("ERROR", e);
        errors.add(error);
    
    }
    
    /**
     * Handle fatal errors during XML validation.
     * These prevent further parsing and indicate serious structural problems.
     */
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        String fatalError = formatErrorMessage("FATAL ERROR", e);
        fatalErrors.add(fatalError);
        errors.add(fatalError); 
        
        System.err.println("RUUUUN! MURIFE RUN! THIS IS A FATAL ERROR \n " + fatalError);
   
        throw e;
    }
    
    /**
     * Format an error message with location information and details.
     */
    private String formatErrorMessage(String type, SAXParseException e) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(type);
        
       
        if (e.getLineNumber() != -1) {
            sb.append(" at line ").append(e.getLineNumber());
            
            if (e.getColumnNumber() != -1) {
                sb.append(", column ").append(e.getColumnNumber());
            }
        }
        
        if (e.getSystemId() != null) {
            sb.append(" in ").append(e.getSystemId());
        }
        
        sb.append(": ").append(e.getMessage());
        
        return sb.toString();
    }
    
    /**
     * Check if any validation errors occurred.
     * @return true if there were errors or fatal errors
     */
    public boolean hasErrors() {
        return !errors.isEmpty() || !fatalErrors.isEmpty();
    }
    
    /**
     * Check if any validation warnings occurred.
     * @return true if there were warnings
     */
    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }
    
    /**
     * Get all validation errors (including fatal errors).
     * @return copy of the errors list
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }
    
    /**
     * Get all validation warnings.
     * @return copy of the warnings list
     */
    public List<String> getWarnings() {
        return new ArrayList<>(warnings);
    }
    
    /**
     * Get fatal errors specifically.
     * @return copy of the fatal errors list
     */
    public List<String> getFatalErrors() {
        return new ArrayList<>(fatalErrors);
    }
    
    /**
     * Get total count of all issues (errors + warnings).
     * @return total issue count
     */
    public int getTotalIssueCount() {
        return errors.size() + warnings.size();
    }
    
    /**
     * Clear all stored errors and warnings.
     * Useful if reusing the handler for multiple validations.
     */
    public void reset() {
        errors.clear();
        warnings.clear();
        fatalErrors.clear();
    }
    
    /**
     * Get a summary of validation results.
     * @return formatted summary string
     */
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        
        if (!hasErrors() && !hasWarnings()) {
            sb.append("No validation issues found.");
        } else {
            sb.append("Validation Summary: ");
            
            if (!errors.isEmpty()) {
                sb.append(errors.size()).append(" error(s)");
            }
            
            if (!warnings.isEmpty()) {
                if (!errors.isEmpty()) {
                    sb.append(", ");
                }
                sb.append(warnings.size()).append(" warning(s)");
            }
        }
        
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("XMLValidationErrorHandler{errors=%d, warnings=%d, fatalErrors=%d}", 
                           errors.size(), warnings.size(), fatalErrors.size());
    }
}