import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.*;
import java.io.File;

public class XmlModifier {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("C:\\Users\\Ogweno\\Desktop\\SkyWorld\\JAVA\\TaskApps\\XMLValidatorV2\\test_files\\pac008_sample.xml"));
        
        // Add CdtrAcct to all OrgnlTxRef elements
        NodeList orgnlTxRefs = doc.getElementsByTagName("OrgnlTxRef");
        for (int i = 0; i < orgnlTxRefs.getLength(); i++) {
            Element orgnlTxRef = (Element) orgnlTxRefs.item(i);
            
            // Create CdtrAcct structure
            Element cdtrAcct = doc.createElement("CdtrAcct");
            Element id = doc.createElement("Id");
            Element othr = doc.createElement("Othr");
            Element othrId = doc.createElement("Id");
            
            // Build the structure
            othr.appendChild(othrId);
            id.appendChild(othr);
            cdtrAcct.appendChild(id);
            
            // Add to OrgnlTxRef
            orgnlTxRef.appendChild(cdtrAcct);
        }
        
        // Save the modified document
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("C:\\Users\\Ogweno\\Desktop\\SkyWorld\\JAVA\\TaskApps\\XMLValidatorV2\\bin\\pac008_sample_modified.xml"));
        transformer.transform(source, result);
        
        System.out.println("Modified XML saved with CdtrAcct elements added");
    }
}