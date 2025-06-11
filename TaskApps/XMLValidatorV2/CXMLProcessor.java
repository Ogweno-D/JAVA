import javax.xml.xpath.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class CXMLProcessor {
    public static void main(String[] args) throws Exception {
        // Load the document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("pacs.008.xml"));
        
        // 1. Read values with XPath
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        
        System.out.println("=== Original Values ===");
        printAllValues(doc, xpath);
        
        // 2. Add CdtrAcct elements
        addCdtrAcctElements(doc);
        
        // 3. Verify the new elements exist
        System.out.println("\n=== After Modification ===");
        System.out.println("CdtrAcct exists: " + 
            xpath.evaluate("//OrgnlTxRef/CdtrAcct", doc) != null);
        
        // Save modified document
        saveDocument(doc, "pacs.008_modified.xml");
    }
    
    private static void printAllValues(Document doc, XPath xpath) throws XPathExpressionException {
        String[] paths = {
            "//MsgId", "//CreDtTm", 
            "//InstgAgt/FinInstnId/Othr/Id", "//InstdAgt/FinInstnId/Othr/Id",
            "//OrgnlMsgId", "//OrgnlMsgNmId", "//OrgnlCreDtTm",
            "//IntrBkSttlmAmt", "//IntrBkSttlmAmt/@Ccy",
            "//SttlmMtd", "//ClrSys/Prtry", "//SvcLvl/Prtry"
        };
        
        for (String path : paths) {
            System.out.println(path + ": " + xpath.evaluate(path, doc));
        }
    }
    
    private static void addCdtrAcctElements(Document doc) {
        NodeList orgnlTxRefs = doc.getElementsByTagName("OrgnlTxRef");
        for (int i = 0; i < orgnlTxRefs.getLength(); i++) {
            Element orgnlTxRef = (Element) orgnlTxRefs.item(i);
            
            if (orgnlTxRef.getElementsByTagName("CdtrAcct").getLength() == 0) {
                Element cdtrAcct = doc.createElement("CdtrAcct");
                // ... rest of element creation code ...
                orgnlTxRef.appendChild(cdtrAcct);
            }
        }
    }
    
    private static void saveDocument(Document doc, String filename) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }
}