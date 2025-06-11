# XML Manipulation Tutorial: DOM vs XPath Methods in Java

## Table of Contents

1. [Overview](#overview)
2. [Setup and Dependencies](#setup-and-dependencies)
3. [Sample XML Structure](#sample-xml-structure)
4. [Method 1: DOM Manipulation (Without XPath)](#method-1-dom-manipulation-without-xpath)
5. [Method 2: XPath Manipulation](#method-2-xpath-manipulation)
6. [Complete Code Examples](#complete-code-examples)
7. [Key Concepts Explained](#key-concepts-explained)
8. [Best Practices](#best-practices)

## Overview

This tutorial demonstrates two approaches to XML manipulation in Java:

- **DOM Methods**: Using standard DOM API methods from `javax.xml.parsers` and `org.w3c.dom`
- **XPath Methods**: Using XPath expressions with `javax.xml.xpath`

Both methods will accomplish the same tasks:

- Extract elements by field_type attributes
- Count specific element types
- Find elements with duplicate checking requirements
- Remove specific XML elements
- Update attribute values

## Setup and Dependencies

### Maven Dependencies

```xml
<dependencies>
    <!-- Built-in Java XML APIs - no additional dependencies needed -->
    <!-- For advanced XML processing, you might want to add: -->
    <dependency>
        <groupId>xerces</groupId>
        <artifactId>xercesImpl</artifactId>
        <version>2.12.2</version>
    </dependency>
</dependencies>
```

### Required Imports

```java
// DOM Processing
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// XPath Processing
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

// Utilities
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
```

## Sample XML Structure

```xml
<?xml version="1.0" encoding="UTF-8"?>
<root>
    <field tag_name="USER_ID" field_type="API_BASED" use="MANDATORY" check_duplicates="true" duplicate_fields="EMAIL,PHONE"/>
    <field tag_name="EMAIL" field_type="API_BASED" use="OPTIONAL" check_duplicates="true" duplicate_fields="USER_ID"/>
    <field tag_name="CUSTOMER_DATA" field_type="TABLE_BASED" use="MANDATORY"/>
    <field tag_name="ORDERS" field_type="TABLE_BASED" use="OPTIONAL"/>
    <field tag_name="PHONE" field_type="API_BASED" use="MANDATORY" check_duplicates="false"/>
    <field tag_name="RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE" field_type="CONFIG" use="OPTIONAL"/>
    <field tag_name="MAX_RESTRICTED_ACCESS_NATIONALITIES" field_type="CONFIG" use="MANDATORY"/>
    <field tag_name="RESTRICTED_ACCESS_NATIONALITIES" field_type="CONFIG" use="MANDATORY"/>
    <section tag_name="PROFILE" field_type="TABLE_BASED" use="OPTIONAL"/>
</root>
```

## Method 1: DOM Manipulation (Without XPath)

### XMLDOMProcessor Class

```java
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XMLDOMProcessor {
    
    /**
     * Parse XML string into Document object
     */
    public static Document parseXML(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xmlString));
        return builder.parse(inputSource);
    }
    
    /**
     * a. Get all elements with field_type API_BASED, list by tag_name
     */
    public static List<String> getAPIBasedElements(Document doc) {
        List<String> apiBasedElements = new ArrayList<>();
        NodeList allNodes = doc.getElementsByTagName("*");
        
        for (int i = 0; i < allNodes.getLength(); i++) {
            Node node = allNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String fieldType = element.getAttribute("field_type");
                String tagName = element.getAttribute("tag_name");
                
                if ("API_BASED".equals(fieldType) && !tagName.isEmpty()) {
                    apiBasedElements.add(tagName);
                }
            }
        }
        
        System.out.println("API_BASED elements by tag_name:");
        apiBasedElements.forEach(System.out::println);
        return apiBasedElements;
    }
    
    /**
     * b. Count elements with field_type TABLE_BASED
     */
    public static int countTableBasedElements(Document doc) {
        int count = 0;
        NodeList allNodes = doc.getElementsByTagName("*");
        
        for (int i = 0; i < allNodes.getLength(); i++) {
            Node node = allNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String fieldType = element.getAttribute("field_type");
                
                if ("TABLE_BASED".equals(fieldType)) {
                    count++;
                }
            }
        }
        
        System.out.println("TABLE_BASED elements count: " + count);
        return count;
    }
    
    /**
     * c. Get elements to be checked for duplicates and associated fields
     */
    public static List<DuplicateCheckElement> getDuplicateCheckElements(Document doc) {
        List<DuplicateCheckElement> duplicateElements = new ArrayList<>();
        NodeList allNodes = doc.getElementsByTagName("*");
        
        for (int i = 0; i < allNodes.getLength(); i++) {
            Node node = allNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String checkDuplicates = element.getAttribute("check_duplicates");
                String tagName = element.getAttribute("tag_name");
                String duplicateFields = element.getAttribute("duplicate_fields");
                
                if ("true".equals(checkDuplicates) && !tagName.isEmpty()) {
                    duplicateElements.add(new DuplicateCheckElement(
                        tagName, 
                        duplicateFields.isEmpty() ? "None" : duplicateFields
                    ));
                }
            }
        }
        
        System.out.println("Elements with duplicate checking:");
        duplicateElements.forEach(item -> 
            System.out.println(item.getTagName() + " -> " + item.getDuplicateFields())
        );
        return duplicateElements;
    }
    
    /**
     * d. Remove specific XML elements
     */
    public static int removeRestrictedElements(Document doc) {
        String[] elementsToRemove = {
            "RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE",
            "MAX_RESTRICTED_ACCESS_NATIONALITIES",
            "RESTRICTED_ACCESS_NATIONALITIES"
        };
        
        List<Element> toRemove = new ArrayList<>();
        NodeList allNodes = doc.getElementsByTagName("*");
        
        for (int i = 0; i < allNodes.getLength(); i++) {
            Node node = allNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String tagName = element.getAttribute("tag_name");
                
                for (String restrictedName : elementsToRemove) {
                    if (restrictedName.equals(tagName)) {
                        toRemove.add(element);
                        break;
                    }
                }
            }
        }
        
        // Remove elements
        for (Element element : toRemove) {
            element.getParentNode().removeChild(element);
        }
        
        System.out.println("Removed " + toRemove.size() + " restricted elements");
        return toRemove.size();
    }
    
    /**
     * e. Update use='MANDATORY' to 'OPTIONAL'
     */
    public static int updateMandatoryToOptional(Document doc) {
        int updateCount = 0;
        NodeList allNodes = doc.getElementsByTagName("*");
        
        for (int i = 0; i < allNodes.getLength(); i++) {
            Node node = allNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String useAttribute = element.getAttribute("use");
                
                if ("MANDATORY".equals(useAttribute)) {
                    element.setAttribute("use", "OPTIONAL");
                    updateCount++;
                }
            }
        }
        
        System.out.println("Updated " + updateCount + " elements from MANDATORY to OPTIONAL");
        return updateCount;
    }
    
    // Helper class for duplicate check elements
    public static class DuplicateCheckElement {
        private String tagName;
        private String duplicateFields;
        
        public DuplicateCheckElement(String tagName, String duplicateFields) {
            this.tagName = tagName;
            this.duplicateFields = duplicateFields;
        }
        
        public String getTagName() { return tagName; }
        public String getDuplicateFields() { return duplicateFields; }
    }
}
```

## Method 2: XPath Manipulation

### XMLXPathProcessor Class

```java
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;

public class XMLXPathProcessor {
    
    private static final XPathFactory xPathFactory = XPathFactory.newInstance();
    
    /**
     * a. Get all elements with field_type API_BASED using XPath
     */
    public static List<String> getAPIBasedElementsXPath(Document doc) throws XPathExpressionException {
        XPath xpath = xPathFactory.newXPath();
        String expression = "//*[@field_type='API_BASED']/@tag_name";
        XPathExpression xPathExpression = xpath.compile(expression);
        
        NodeList result = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
        List<String> tagNames = new ArrayList<>();
        
        for (int i = 0; i < result.getLength(); i++) {
            Node node = result.item(i);
            tagNames.add(node.getNodeValue());
        }
        
        System.out.println("API_BASED elements by tag_name (XPath):");
        tagNames.forEach(System.out::println);
        return tagNames;
    }
    
    /**
     * b. Count elements with field_type TABLE_BASED using XPath
     */
    public static int countTableBasedElementsXPath(Document doc) throws XPathExpressionException {
        XPath xpath = xPathFactory.newXPath();
        String expression = "count(//*[@field_type='TABLE_BASED'])";
        XPathExpression xPathExpression = xpath.compile(expression);
        
        Double result = (Double) xPathExpression.evaluate(doc, XPathConstants.NUMBER);
        int count = result.intValue();
        
        System.out.println("TABLE_BASED elements count (XPath): " + count);
        return count;
    }
    
    /**
     * c. Get elements to be checked for duplicates using XPath
     */
    public static List<XMLDOMProcessor.DuplicateCheckElement> getDuplicateCheckElementsXPath(Document doc) 
            throws XPathExpressionException {
        XPath xpath = xPathFactory.newXPath();
        String expression = "//*[@check_duplicates='true']";
        XPathExpression xPathExpression = xpath.compile(expression);
        
        NodeList result = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
        List<XMLDOMProcessor.DuplicateCheckElement> duplicateElements = new ArrayList<>();
        
        for (int i = 0; i < result.getLength(); i++) {
            Element element = (Element) result.item(i);
            String tagName = element.getAttribute("tag_name");
            String duplicateFields = element.getAttribute("duplicate_fields");
            
            duplicateElements.add(new XMLDOMProcessor.DuplicateCheckElement(
                tagName, 
                duplicateFields.isEmpty() ? "None" : duplicateFields
            ));
        }
        
        System.out.println("Elements with duplicate checking (XPath):");
        duplicateElements.forEach(item -> 
            System.out.println(item.getTagName() + " -> " + item.getDuplicateFields())
        );
        return duplicateElements;
    }
    
    /**
     * d. Remove restricted elements using XPath
     */
    public static int removeRestrictedElementsXPath(Document doc) throws XPathExpressionException {
        XPath xpath = xPathFactory.newXPath();
        String[] expressions = {
            "//*[@tag_name='RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE']",
            "//*[@tag_name='MAX_RESTRICTED_ACCESS_NATIONALITIES']",
            "//*[@tag_name='RESTRICTED_ACCESS_NATIONALITIES']"
        };
        
        List<Element> elementsToRemove = new ArrayList<>();
        
        for (String expression : expressions) {
            XPathExpression xPathExpression = xpath.compile(expression);
            NodeList result = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            
            for (int i = 0; i < result.getLength(); i++) {
                elementsToRemove.add((Element) result.item(i));
            }
        }
        
        // Remove elements
        for (Element element : elementsToRemove) {
            element.getParentNode().removeChild(element);
        }
        
        System.out.println("Removed " + elementsToRemove.size() + " restricted elements (XPath)");
        return elementsToRemove.size();
    }
    
    /**
     * e. Update use='MANDATORY' to 'OPTIONAL' using XPath
     */
    public static int updateMandatoryToOptionalXPath(Document doc) throws XPathExpressionException {
        XPath xpath = xPathFactory.newXPath();
        String expression = "//*[@use='MANDATORY']";
        XPathExpression xPathExpression = xpath.compile(expression);
        
        NodeList result = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
        
        for (int i = 0; i < result.getLength(); i++) {
            Element element = (Element) result.item(i);
            element.setAttribute("use", "OPTIONAL");
        }
        
        System.out.println("Updated " + result.getLength() + " elements from MANDATORY to OPTIONAL (XPath)");
        return result.getLength();
    }
    
    /**
     * Advanced XPath query examples
     */
    public static void demonstrateAdvancedXPath(Document doc) throws XPathExpressionException {
        XPath xpath = xPathFactory.newXPath();
        
        // Get elements with multiple conditions
        String complexExpression = "//*[@field_type='API_BASED' and @use='MANDATORY']/@tag_name";
        XPathExpression xPathExpression = xpath.compile(complexExpression);
        NodeList result = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
        
        System.out.println("API_BASED AND MANDATORY elements:");
        for (int i = 0; i < result.getLength(); i++) {
            System.out.println(result.item(i).getNodeValue());
        }
        
        // Get elements by position
        String positionExpression = "//*[@field_type='API_BASED'][1]/@tag_name";
        String firstAPIBased = (String) xpath.evaluate(positionExpression, doc, XPathConstants.STRING);
        System.out.println("First API_BASED element: " + firstAPIBased);
        
        // Check if elements exist
        String existsExpression = "boolean(//*[@tag_name='USER_ID'])";
        Boolean exists = (Boolean) xpath.evaluate(existsExpression, doc, XPathConstants.BOOLEAN);
        System.out.println("USER_ID exists: " + exists);
    }
}
```

## Complete Code Examples

### Main Application Class

```java
public class XMLManipulationDemo {
    
    private static final String SAMPLE_XML = """
        <?xml version="1.0" encoding="UTF-8"?>
        <root>
            <field tag_name="USER_ID" field_type="API_BASED" use="MANDATORY" check_duplicates="true" duplicate_fields="EMAIL,PHONE"/>
            <field tag_name="EMAIL" field_type="API_BASED" use="OPTIONAL" check_duplicates="true" duplicate_fields="USER_ID"/>
            <field tag_name="CUSTOMER_DATA" field_type="TABLE_BASED" use="MANDATORY"/>
            <field tag_name="ORDERS" field_type="TABLE_BASED" use="OPTIONAL"/>
            <field tag_name="PHONE" field_type="API_BASED" use="MANDATORY" check_duplicates="false"/>
            <field tag_name="RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE" field_type="CONFIG" use="OPTIONAL"/>
            <field tag_name="MAX_RESTRICTED_ACCESS_NATIONALITIES" field_type="CONFIG" use="MANDATORY"/>
            <field tag_name="RESTRICTED_ACCESS_NATIONALITIES" field_type="CONFIG" use="MANDATORY"/>
            <section tag_name="PROFILE" field_type="TABLE_BASED" use="OPTIONAL"/>
        </root>
        """;
    
    public static void main(String[] args) {
        try {
            // Demonstrate DOM processing
            System.out.println("=== DOM Method Results ===");
            demonstrateDOMProcessing();
            
            System.out.println("\n" + "=".repeat(50) + "\n");
            
            // Demonstrate XPath processing
            System.out.println("=== XPath Method Results ===");
            demonstrateXPathProcessing();
            
        } catch (Exception e) {
            System.err.println("Error processing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void demonstrateDOMProcessing() throws Exception {
        Document doc = XMLDOMProcessor.parseXML(SAMPLE_XML);
        
        XMLDOMProcessor.getAPIBasedElements(doc);
        System.out.println();
        
        XMLDOMProcessor.countTableBasedElements(doc);
        System.out.println();
        
        XMLDOMProcessor.getDuplicateCheckElements(doc);
        System.out.println();
        
        XMLDOMProcessor.removeRestrictedElements(doc);
        System.out.println();
        
        XMLDOMProcessor.updateMandatoryToOptional(doc);
    }
    
    private static void demonstrateXPathProcessing() throws Exception {
        Document doc = XMLDOMProcessor.parseXML(SAMPLE_XML);
        
        XMLXPathProcessor.getAPIBasedElementsXPath(doc);
        System.out.println();
        
        XMLXPathProcessor.countTableBasedElementsXPath(doc);
        System.out.println();
        
        XMLXPathProcessor.getDuplicateCheckElementsXPath(doc);
        System.out.println();
        
        XMLXPathProcessor.removeRestrictedElementsXPath(doc);
        System.out.println();
        
        XMLXPathProcessor.updateMandatoryToOptionalXPath(doc);
        System.out.println();
        
        XMLXPathProcessor.demonstrateAdvancedXPath(doc);
    }
}
```

### Utility Class for XML Operations

```java
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XMLUtils {
    
    /**
     * Convert Document to XML string
     */
    public static String documentToString(Document doc) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        return writer.toString();
    }
    
    /**
     * Pretty print XML document
     */
    public static void printDocument(Document doc) {
        try {
            String xmlString = documentToString(doc);
            System.out.println("XML Document:");
            System.out.println(xmlString);
        } catch (Exception e) {
            System.err.println("Error printing document: " + e.getMessage());
        }
    }
    
    /**
     * Validate XML structure
     */
    public static boolean isValidXML(String xmlString) {
        try {
            XMLDOMProcessor.parseXML(xmlString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
```

### Unit Test Example

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.w3c.dom.Document;
import java.util.List;

public class XMLProcessorTest {
    
    private Document testDocument;
    private final String testXML = """
        <?xml version="1.0" encoding="UTF-8"?>
        <root>
            <field tag_name="USER_ID" field_type="API_BASED" use="MANDATORY" check_duplicates="true"/>
            <field tag_name="ORDERS" field_type="TABLE_BASED" use="OPTIONAL"/>
        </root>
        """;
    
    @BeforeEach
    void setUp() throws Exception {
        testDocument = XMLDOMProcessor.parseXML(testXML);
    }
    
    @Test
    void testGetAPIBasedElements() {
        List<String> result = XMLDOMProcessor.getAPIBasedElements(testDocument);
        assertEquals(1, result.size());
        assertEquals("USER_ID", result.get(0));
    }
    
    @Test
    void testCountTableBasedElements() {
        int count = XMLDOMProcessor.countTableBasedElements(testDocument);
        assertEquals(1, count);
    }
    
    @Test
    void testUpdateMandatoryToOptional() {
        int updateCount = XMLDOMProcessor.updateMandatoryToOptional(testDocument);
        assertEquals(1, updateCount);
    }
    
    @Test
    void testXPathAPIBasedElements() throws Exception {
        List<String> result = XMLXPathProcessor.getAPIBasedElementsXPath(testDocument);
        assertEquals(1, result.size());
        assertEquals("USER_ID", result.get(0));
    }
    
    @Test
    void testXPathCountTableBased() throws Exception {
        int count = XMLXPathProcessor.countTableBasedElementsXPath(testDocument);
        assertEquals(1, count);
    }
}
```

## Key Concepts Explained

### DOM Processing in Java

1. **DocumentBuilder**: Factory pattern for creating DOM parsers
2. **NodeList**: Collection of nodes returned by DOM queries
3. **Element vs Node**: Elements are specific types of nodes with attributes
4. **Node Types**: Check `getNodeType()` to ensure you're working with elements
5. **Attribute Access**: Use `getAttribute()` and `setAttribute()` for attribute manipulation

### XPath Processing in Java

1. **XPathFactory**: Factory for creating XPath objects
2. **XPathExpression**: Compiled XPath queries for better performance
3. **XPathConstants**: Defines return types (NODESET, STRING, NUMBER, BOOLEAN)
4. **Namespace Handling**: XPath can handle XML namespaces when configured
5. **Performance**: Compile XPath expressions once and reuse them

### XPath Syntax Quick Reference

| Expression | Description | Example |
|------------|-------------|---------|
| `//*` | All elements | `//*[@field_type='API_BASED']` |
| `@attribute` | Attribute value | `//*/@tag_name` |
| `[condition]` | Filter predicate | `//*[@use='MANDATORY']` |
| `count()` | Count nodes | `count(//*[@field_type='TABLE_BASED'])` |
| `and`, `or` | Logical operators | `//*[@field_type='API_BASED' and @use='MANDATORY']` |
| `[position]` | Position-based | `//*[@field_type='API_BASED'][1]` |
| `boolean()` | Boolean test | `boolean(//*[@tag_name='USER_ID'])` |

## Best Practices

### Performance Optimization

1. **Reuse Parsers**: Create DocumentBuilder once and reuse
2. **Compile XPath**: Use XPathExpression for repeated queries
3. **Batch Operations**: Collect elements first, then modify to avoid concurrent modification
4. **Memory Management**: Process large XML files with streaming APIs when possible

### Error Handling

```java
public class XMLProcessorWithErrorHandling {
    
    public static List<String> safeGetAPIBasedElements(Document doc) {
        try {
            return XMLDOMProcessor.getAPIBasedElements(doc);
        } catch (Exception e) {
            System.err.println("Error getting API based elements: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public static List<String> safeXPathQuery(Document doc, String xpathExpression) {
        List<String> results = new ArrayList<>();
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nodes = (NodeList) xpath.evaluate(xpathExpression, doc, XPathConstants.NODESET);
            
            for (int i = 0; i < nodes.getLength(); i++) {
                results.add(nodes.item(i).getTextContent());
            }
        } catch (XPathExpressionException e) {
            System.err.println("XPath expression error: " + e.getMessage());
        }
        return results;
    }
}
```

### Thread Safety

```java
public class ThreadSafeXMLProcessor {
    
    // XPathFactory is not thread-safe, create per thread
    private static final ThreadLocal<XPath> XPATH_THREAD_LOCAL = 
        ThreadLocal.withInitial(() -> XPathFactory.newInstance().newXPath());
    
    public static int countElementsThreadSafe(Document doc, String xpathExpression) 
            throws XPathExpressionException {
        XPath xpath = XPATH_THREAD_LOCAL.get();
        Double result = (Double) xpath.evaluate("count(" + xpathExpression + ")", doc, XPathConstants.NUMBER);
        return result.intValue();
    }
}
```

### Memory Efficient Processing

```java
public class StreamingXMLProcessor {
    
    /**
     * Process large XML files with minimal memory footprint
     */
    public static void processLargeXMLFile(String filePath) throws Exception {
        // Use StAX for streaming
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filePath));
        
        while (reader.hasNext()) {
            int event = reader.next();
            
            if (event == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();
                String fieldType = reader.getAttributeValue(null, "field_type");
                
                if ("API_BASED".equals(fieldType)) {
                    String tagName = reader.getAttributeValue(null, "tag_name");
                    System.out.println("Found API_BASED element: " + tagName);
                }
            }
        }
        reader.close();
    }
}
```

## Conclusion

This tutorial provides comprehensive coverage of XML manipulation in Java using both DOM and XPath approaches. Key takeaways:

- **DOM Methods** are straightforward and work well for simple operations
- **XPath** provides powerful querying capabilities with concise syntax
- **Performance** considerations favor compiled XPath expressions for repeated queries
- **Error Handling** is crucial for robust XML processing applications
- **Thread Safety** requires careful consideration when using XPath in multi-threaded environments

Choose the approach that best fits your specific requirements, considering factors like XML complexity, performance needs, and team expertise with XPath syntax.
