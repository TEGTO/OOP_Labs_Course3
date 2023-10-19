package parsers;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import parsers.XMLValidator;

public class XMLValidatorTest {

    @Test
    public void testValidXML() {
        boolean result = XMLValidator.validate(Pathes.XML_PATH, Pathes.XSD_PATH);
        assertTrue(result, "The XML should be valid against the schema.");
    }

    // This test assumes you have an intentionally invalid XML for testing purposes.
    @Test
    public void testInvalidXML() {
        boolean result = XMLValidator.validate(Pathes.XML_INVALID_PATH, Pathes.XSD_PATH);
        assertFalse(result, "The XML should be invalid against the schema.");
    }

    @Test
    public void testNonExistentXML() {
        String nonExistentXMLPath = "src/main/resources/nonexistent.xml";
        boolean result = XMLValidator.validate(nonExistentXMLPath, Pathes.XSD_PATH);
        assertFalse(result, "The validation should fail for a non-existent XML.");
    }

    @Test
    public void testNonExistentXSD() {
        String nonExistentXSDPath = "src/main/resources/nonexistent.xsd";
        boolean result = XMLValidator.validate(Pathes.XML_PATH, nonExistentXSDPath);
        assertFalse(result, "The validation should fail for a non-existent XSD.");
    }
}