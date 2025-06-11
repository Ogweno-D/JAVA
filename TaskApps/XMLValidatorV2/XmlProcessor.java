import javax.xml.xpath.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class XmlProcessor {
    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("C:\\Users\\Ogweno\\Desktop\\SkyWorld\\JAVA\\TaskApps\\XMLValidatorV2\\test_files\\pac008_sample.xml"));
        
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        
        // Read all values using XPath
        System.out.println("MsgId: " + getValue(doc, xpath, "//MsgId"));
        System.out.println("CreDtTm: " + getValue(doc, xpath, "//CreDtTm"));
        System.out.println("InstgAgt Id: " + getValue(doc, xpath, "//InstgAgt/FinInstnId/Othr/Id"));
        System.out.println("InstdAgt Id: " + getValue(doc, xpath, "//InstdAgt/FinInstnId/Othr/Id"));
        System.out.println("OrgnlMsgId: " + getValue(doc, xpath, "//OrgnlMsgId"));
        System.out.println("OrgnlMsgNmId: " + getValue(doc, xpath, "//OrgnlMsgNmId"));
        System.out.println("OrgnlCreDtTm: " + getValue(doc, xpath, "//OrgnlCreDtTm"));
        System.out.println("IntrBkSttlmAmt: " + getValue(doc, xpath, "//IntrBkSttlmAmt"));
        System.out.println("IntrBkSttlmAmt Ccy: " + getAttribute(doc, xpath, "//IntrBkSttlmAmt/@Ccy"));
        System.out.println("SttlmMtd: " + getValue(doc, xpath, "//SttlmMtd"));
        System.out.println("ClrSys Prtry: " + getValue(doc, xpath, "//ClrSys/Prtry"));
        System.out.println("SvcLvl Prtry: " + getValue(doc, xpath, "//SvcLvl/Prtry"));
    }
    
    private static String getValue(Document doc, XPath xpath, String expression) throws XPathExpressionException {
        return xpath.evaluate(expression, doc);
    }
    
    private static String getAttribute(Document doc, XPath xpath, String expression) throws XPathExpressionException {
        return xpath.evaluate(expression, doc);
    }
}