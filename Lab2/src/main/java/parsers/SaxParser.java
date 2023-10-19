package parsers;

import device.Device;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxParser<T>
{
    public List<T> parseSAX(File xml, MyHandler handler) throws SAXException, IOException, ParserConfigurationException
    {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(xml, handler);
        return handler.getHandledList();
    }
    public List<T> parseSAX(String filePath, MyHandler handler) throws SAXException, IOException, ParserConfigurationException
    {
        File xml = new File(filePath);
        return parseSAX(xml, handler);
    }
}
