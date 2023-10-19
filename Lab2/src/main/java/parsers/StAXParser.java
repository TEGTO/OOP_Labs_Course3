package parsers;
import myLogger.MyLogger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class StAXParser<T>
{
    public List<T> parseStAX(FileInputStream xml, MyHandler handler) throws SAXException, IOException, ParserConfigurationException
    {
        try
        {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(xml);
            while (reader.hasNext())
            {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement())
                {
                    StartElement startElement = nextEvent.asStartElement();
                    nextEvent = reader.nextEvent();
                    if (nextEvent.isCharacters())
                    {
                        List<Attribute> attributesList = new ArrayList<>();
                        Iterator<Attribute> iter = startElement.getAttributes();
                        while (iter.hasNext())
                            attributesList.add(iter.next());
                        Map<String, String> attributeMap = new HashMap<>();
                        for (Attribute attribute : attributesList)
                            attributeMap.put(attribute.getName().getLocalPart(), attribute.getValue());
                        handler.setField(startElement.getName().getLocalPart(), nextEvent.asCharacters().getData(), attributeMap);
                    }
                }
            }
            return handler.getHandledList();
        }
        catch (Exception ex)
        {
            MyLogger.printErrorMessage(ex.getMessage());
        }
        return null;
    }
}
