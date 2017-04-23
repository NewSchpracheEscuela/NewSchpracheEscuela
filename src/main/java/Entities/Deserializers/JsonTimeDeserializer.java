package Entities.Deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonTimeDeserializer extends JsonDeserializer<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "HH:mm:ss.SSS");

    @Override
    public Date deserialize(JsonParser paramJsonParser,
                            DeserializationContext paramDeserializationContext)
            throws IOException{
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (Exception e) {

        }

        return paramDeserializationContext.parseDate(str);
    }
}