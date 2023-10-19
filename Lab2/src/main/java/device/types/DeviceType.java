package device.types;
public class DeviceType
{
    private Boolean isPeripheral;
    private Short energyConsumption;
    private Boolean hasCooler;
    private String port;
    private String group;

    @Override
    public String toString() {
        return "Options{" +
                "IsPeripheral = " + isPeripheral +
                ", energyConsumption = " + energyConsumption +
                ", presenceCooler = " + hasCooler +
                ", port=" + port +
                ", group=" + group +
                '}';
    }
    public Boolean getPeripheral() {return isPeripheral;}
    public void setPeripheral(Boolean peripheral) {isPeripheral = peripheral;}
    public int getEnergyConsumption() {return energyConsumption;}
    public void setEnergyConsumption(short value) {this.energyConsumption = value;}

    public boolean getHasCooler() {return hasCooler;}
    public void setHasCooler(boolean value) {this.hasCooler = value;}

    public String getPort() {return port;}
    public void setPort(String value) {this.port = value;}

    public String getGroup() {return group;}
    public void setGroup(String value) {this.group = value;}
}
