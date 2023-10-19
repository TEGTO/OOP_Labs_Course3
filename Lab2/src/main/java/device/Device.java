package device;
import device.types.DeviceType;

import java.util.Comparator;

public class Device implements Comparable<Device>
{
    private Integer id;
    private String name;
    private String origin;
    private Float price;
    private Boolean isCritical;
    private DeviceType type = new DeviceType();

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getOrigin() {return origin;}
    public void setOrigin(String origin) {this.origin = origin;}
    public Float getPrice() {return price;}
    public void setPrice(Float price) {this.price = price;}
    public Boolean getCritical() {return isCritical;}
    public void setCritical(Boolean critical) {isCritical = critical;}
    public DeviceType getType() {return type;}
    public void setType(DeviceType type) {this.type = type;}
    @Override
    public int compareTo(Device o) {return this.getPrice().compareTo(o.getPrice());}
}
