package parsers;
import device.Device;
import device.DeviceFields;
import device.types.DeviceType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceHandler extends MyHandler<Device>
{
    private StringBuilder elementValue;
    private List<Device> deviceList = new ArrayList<>();

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        if (elementValue == null)
            elementValue = new StringBuilder();
        else
            elementValue.append(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException
    {
        List<Device> deviceList = new ArrayList<>();
    }
    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException
    {
        switch (qName)
        {
            case DeviceFields.DEVICE:
                Device device = new Device();
                deviceList.add(device);
                break;
            case DeviceFields.TYPE:
                DeviceType type = new DeviceType();
                latestDevice().setType(type);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        switch (qName)
        {
            case DeviceFields.ID:
                latestDevice().setId(Integer.parseInt(elementValue.toString().trim()));
                break;
            case DeviceFields.NAME:
                latestDevice().setName(elementValue.toString().trim());
                break;
            case DeviceFields.ORIGIN:
                latestDevice().setOrigin(elementValue.toString().trim());
                break;
            case DeviceFields.PRICE:
                latestDevice().setPrice(Float.parseFloat(elementValue.toString().trim()));
                break;
            case DeviceFields.IS_CRITICAL:
                latestDevice().setCritical(Boolean.parseBoolean(elementValue.toString().trim()));
                break;
            case DeviceFields.IS_PERIPHERAL:
                latestDevice().getType().setPeripheral(Boolean.parseBoolean(elementValue.toString().trim()));
                break;
            case DeviceFields.ENERGY_CONSUMPTION:
                latestDevice().getType().setEnergyConsumption(Short.parseShort(elementValue.toString().trim()));
                break;
            case DeviceFields.COOLER:
                latestDevice().getType().setHasCooler(Boolean.parseBoolean(elementValue.toString().trim()));
                break;
            case DeviceFields.GROUP:
                latestDevice().getType().setGroup(elementValue.toString().trim());
                break;
            case DeviceFields.PORT:
                latestDevice().getType().setPort(elementValue.toString().trim());
                break;
        }
        elementValue.setLength(0);
    }
    @Override
    public void setField(String propName, String propData,Map<String, String> attributes)
    {
        switch (propName)
        {
            case DeviceFields.DEVICE:
                Device device = new Device();
                deviceList.add(device);
                break;
            case DeviceFields.TYPE:
                DeviceType type = new DeviceType();
                latestDevice().setType(type);
                break;
            case DeviceFields.ID:
                latestDevice().setId(Integer.parseInt(propData.trim()));
                break;
            case DeviceFields.NAME:
                latestDevice().setName(propData.trim());
                break;
            case DeviceFields.ORIGIN:
                latestDevice().setOrigin(propData.trim());
                break;
            case DeviceFields.PRICE:
                latestDevice().setPrice(Float.parseFloat(propData.trim()));
                break;
            case DeviceFields.IS_CRITICAL:
                latestDevice().setCritical(Boolean.parseBoolean(propData.trim()));
                break;
            case DeviceFields.IS_PERIPHERAL:
                latestDevice().getType().setPeripheral(Boolean.parseBoolean(propData.trim()));
                break;
            case DeviceFields.ENERGY_CONSUMPTION:
                latestDevice().getType().setEnergyConsumption(Short.parseShort(propData.trim()));
                break;
            case DeviceFields.COOLER:
                latestDevice().getType().setHasCooler(Boolean.parseBoolean(propData.trim()));
                break;
            case DeviceFields.GROUP:
                latestDevice().getType().setGroup(propData.trim());
                break;
            case DeviceFields.PORT:
                latestDevice().getType().setPort(propData.trim());
                break;
        }
    }
    @Override
    public List<Device> getHandledList() {return deviceList;}
    @Override
    public String getRootElName() {return DeviceFields.DEVICE;}
    private Device latestDevice() {return deviceList.get(deviceList.size() - 1);}
}
