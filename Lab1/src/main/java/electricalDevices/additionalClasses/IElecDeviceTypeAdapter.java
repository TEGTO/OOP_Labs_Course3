package electricalDevices.additionalClasses;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import electricalDevices.IElecDevice;
import java.io.IOException;

public class IElecDeviceTypeAdapter extends TypeAdapter<IElecDevice>
{
    @Override
    public void write(JsonWriter out, IElecDevice device) throws IOException
    {
        out.beginObject();
        out.name("type").value(device.getClass().getName());
        out.name("properties");
        // This uses the default Gson serialization for the object's properties
        Gson defaultGson = new Gson();
        defaultGson.toJson(device, device.getClass(), out);
        out.endObject();
    }
    @Override
    public IElecDevice read(JsonReader in) throws IOException
    {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(in).getAsJsonObject();
        String className = jsonObject.get("type").getAsString();
        try
        {
            Class<?> clazz = Class.forName(className);
            return (IElecDevice) new Gson().fromJson(jsonObject.get("properties"), clazz);
        }
        catch (ClassNotFoundException e)
        {
            throw new JsonParseException("Unable to instantiate " + className, e);
        }
    }
}
