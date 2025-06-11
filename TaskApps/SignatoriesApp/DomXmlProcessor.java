
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//Which is better? Importing all or importing one by one?

import org.w3c.dom.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DomXmlProcessor implements  XmlProcessor {
    private final Document doc;

     public DomXmlProcessor(Path xmlFilePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.doc = builder.parse(xmlFilePath.toFile());
    }

    // Task a.

    @Override
    public void printApiBasedElements(){
        System.out.println("API_BASED elements by the tag_name(DOM: )");
        traverseElementsforApiBased(doc.getDocumentElement());
    }

    // We need a traversal  for the XML Doc.

    private void traverseElementsforApiBased(Node node){
        if(node.getNodeType() == Node.ELEMENT_NODE){

            Element element = (Element) node;
            if("API_BASED".equals(element.getAttribute("field_type"))){
                String tagName = element.getAttribute("tag_name");
                if (!tagName.isEmpty()) {
                    System.out.println(tagName);  
                }
            }
        }

        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); i++){
            traverseElementsforApiBased(children.item(i));
        }
    }

    // Task b. Count TABLR_BASED elements
    @Override
    public int countTableBasedElements(){
        return  countElementsByFieldType(
            doc.getDocumentElement(), "TABLE_BASED");
    }

    private int countElementsByFieldType(Node node, String fieldType){
        int count =0;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            if(fieldType.equals(element.getAttribute("field_type"))){
                count++;
            }   
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            count += countElementsByFieldType(children.item(i), fieldType);
            
        }

        return  count;
    }

    // Task c: Finding Duplicate Elements
    @Override
    public void printDuplicateCheckElements(){
        System.out.println("Elements with duplicate checking: ");

        findDuplicateCheckElements(doc.getDocumentElement());
    }

    private void findDuplicateCheckElements(Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE) {

            Element element = (Element) node;

            if("true".equalsIgnoreCase(element.getAttribute("check_duplicates"))){
                System.out.print("Element: " + element.getAttribute("tag_name") + " - Fields:");
                printFieldNames(element);
                System.out.println();
            }

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                findDuplicateCheckElements(children.item(i));
            }
            
        }

    }

    // Function to print the fieldNames
    private void printFieldNames(Element parent){
        NodeList fields = parent.getElementsByTagName("field");

        for (int i = 0; i < fields.getLength(); i++) {
            Element field = (Element) fields.item(i);
            System.out.print(field.getAttribute("name") + " ");
        }
    }


    // Task d.Remove Specified Elements
    @Override
    public void removeRestrictedElements(){
        String [] elementsToRemove ={
            "RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE",
            "MAX_RESTRICTED_ACCESS_NATIONALITIES",
            "RESTICTED_ACCESS_NATIONALITIES"
        };

        for (String tagName: elementsToRemove){
            removeElementByTagName(tagName);
        }
    }

    private void removeElementByTagName(String tagName){
        NodeList nodes = doc.getElementsByTagName(tagName);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            node.getParentNode().removeChild(node);
        }
    }

    // Task e. Update Mandatory to Optional (CAPS INTENDED)
    // (Amazing Music : https://youtu.be/EnPeMwO-iio)
    @Override
    public void updateMandatoryToOptional(){
        updateAttributeValues(doc.getDocumentElement(), "use", "MANDATORY", "OPTIONAL");

    }
    
    private void updateAttributeValues(Node node, String attrName, String oldValue, String newValue){
        if (node.getNodeType() == Node.ELEMENT_NODE){
            Element element =(Element) node;
            
            if(oldValue.equals(element.getAttribute(attrName))){
                element.setAttribute(attrName, newValue);
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            updateAttributeValues(children.item(i), attrName, oldValue, newValue);
        }
    }

    // Implement this later

    @Override
    public void saveDocument(Path outputPath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(outputPath.toFile());

        transformer.transform(source, result);
    }
}
