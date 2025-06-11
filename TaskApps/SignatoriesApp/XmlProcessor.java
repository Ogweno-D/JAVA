
import java.nio.file.Path;

public interface XmlProcessor{

    // Well think of customizong the Exception
    // TODO: Document the various methods

    void printApiBasedElements() throws  Exception;
    int countTableBasedElements() throws  Exception;
    void printDuplicateCheckElements() throws  Exception;
    void removeRestrictedElements( ) throws  Exception;
    void updateMandatoryToOptional() throws  Exception;
    void saveDocument( Path outputPath) throws  Exception;
}