import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

public class Example2 {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory f = mapper.getFactory(); // may alternatively construct directly too

        // First: write simple JSON output
        File jsonFile = new File("test2.json");
        JsonGenerator g = f.createGenerator(jsonFile, JsonEncoding.UTF8);
        // write JSON: { "message" : "Hello world!" }
        g.writeStartObject();
        g.writeStringField("message", "Hello world!");
        g.writeEndObject();
        g.close();

        // Second: read file back
        File file2 = new File(Resources.getResource("test2.json").getFile());
        JsonParser p = f.createParser(file2);

        JsonToken t = p.nextToken(); // Should be JsonToken.START_OBJECT
        t = p.nextToken(); // JsonToken.FIELD_NAME
        if ((t != JsonToken.FIELD_NAME) || !"message".equals(p.getCurrentName())) {
            // handle error
        }
        t = p.nextToken();
        if (t != JsonToken.VALUE_STRING) {
            // similarly
        }
        String msg = p.getText();
        System.out.printf("My message to you is: %s!\n", msg);
        p.close();
    }
}
