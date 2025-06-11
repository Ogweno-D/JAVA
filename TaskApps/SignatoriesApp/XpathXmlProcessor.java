
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import java.nio.file.Path;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XpathXmlProcessor implements  XmlProcessor{
    
    private final Document doc ;
    private final XPath xpath;

    public XpathXmlProcessor(Path xmlFilePath) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.doc = builder.parse(xmlFilePath.toFile());
        this.xpath = XPathFactory.newInstance().newXPath();
    }

    // Task A
    @Override
    public void printApiBasedElements() throws XPathExpressionException{
        System.out.println("API_BASED elements by tag_name(XPath): ");

        XPathExpression expr = xpath.compile("*//*[@field_type='API_BASED']/@tag_name");

        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
    }

    // Task B
    @Override
    public int countTableBasedElements() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("count(//*[@field_type='TABLE_BASED'])");
        Double count = (Double) expr.evaluate(doc, XPathConstants.NUMBER);

        return count.intValue();
    }

    // Task.C
    // Find duplicate check Elements
    @Override
    public void printDuplicateCheckElements() throws XPathExpressionException{
        System.out.println("Elements with duplicate checking:");
        XPathExpression expr = xpath.compile("//*[@check_duplicates='true']");

        NodeList nodes = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            
            System.out.print("Element:" + element.getAttribute("tag_name") + "- Fields: ");
            
            NodeList fields = element.getElementsByTagName("field");
            for (int j = 0; j < fields.getLength(); j++) {
                System.out.print(((Element) fields.item(j)).getAttribute("name") + " ");
                
            }
        }

        System.out.println();
    }

    // Task d. Remove Specified Elements
    @Override
    public void  removeRestrictedElements() throws  XPathExpressionException{

        String [] elementsToRemove = {
            "RESTRICTED_ACCESS_NATIONALITIES_MATCH_TYPE",
            "MAX_RESTRICTED_ACCESS_NATIONALITIES",
            "RESTRICTED_ACCESS_NATIONALITIES"
        };

        for (String tagName : elementsToRemove) {
            XPathExpression expr = xpath.compile("//" + tagName);

            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength()-1; i--) {
                Node node = nodes.item(i);
                node.getParentNode().removeChild(node);
            }
        }

    }

    // / Task e: Update MANDATORY to OPTIONAL
    @Override
    public void updateMandatoryToOptional() throws XPathExpressionException {
        XPathExpression expr = xpath.compile("//*[@use='MANDATORY']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            ((Element)nodes.item(i)).setAttribute("use", "OPTIONAL");
        }
    }

    @Override
    public void saveDocument(Path outputPath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(outputPath.toFile());
        transformer.transform(source, result);
    }
}


