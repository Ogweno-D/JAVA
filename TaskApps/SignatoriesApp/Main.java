import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            Path inputPath = Path.of("C:\\Users\\Ogweno\\Desktop\\SkyWorld\\JAVA\\TaskApps\\SignatoriesApp\\signatories_model_info.xml");
            Path domOutputPath = Path.of("C:\\Users\\Ogweno\\Desktop\\SkyWorld\\JAVA\\TaskApps\\SignatoriesApp\\bin\\output_dom.xml");
            Path xpathOutputPath = Path.of("C:\\Users\\Ogweno\\Desktop\\SkyWorld\\JAVA\\TaskApps\\SignatoriesApp\\bin\\output_xpath.xml");

            System.out.println("=== Processing with DOM (no XPath) ===");
            processWithDom(inputPath, domOutputPath);

            System.out.println("\n=== Processing with XPath ===");
            processWithXPath(inputPath, xpathOutputPath);

        } catch (Exception e) {
            System.err.println("Error processing XML: " + e.getMessage());
            // I dont get why the exensions are flagging this beauty! but well
            // A stack trace is important in the long run
            // e.printStackTrace();
        }
    }

    private static void processWithDom(Path inputPath, Path outputPath) throws Exception {
        XmlProcessor processor = new DomXmlProcessor(inputPath);

        // Task a
        processor.printApiBasedElements();

        // Task b
        System.out.println("TABLE_BASED count: " + processor.countTableBasedElements());

        // Task c
        processor.printDuplicateCheckElements();

        // Task d
        processor.removeRestrictedElements();

        // Task e
        processor.updateMandatoryToOptional();

        // Save results
        processor.saveDocument(outputPath);
        System.out.println("DOM processing complete. Output saved to: " + outputPath);
    }

    private static void processWithXPath(Path inputPath, Path outputPath) throws Exception {
        XmlProcessor processor = new XpathXmlProcessor(inputPath);

        // Task a
        processor.printApiBasedElements();

        // Task b
        System.out.println("TABLE_BASED count: " + processor.countTableBasedElements());

        // Task c
        processor.printDuplicateCheckElements();

        // Task d
        processor.removeRestrictedElements();

        // Task e
        processor.updateMandatoryToOptional();

        // Save results
        processor.saveDocument(outputPath);
        System.out.println("XPath processing complete. Output saved to: " + outputPath);
    }
}