import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;

public final class Example {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        MyValue value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
        System.out.println(value.name + ", " + value.age);

        String valueString = mapper.writeValueAsString(value);
        System.out.println(valueString);

        File file = new File(Resources.getResource("test.json").getFile());
        MyValue value2 = mapper.readValue(file, MyValue.class);
        System.out.println(value2.name + ", " + value2.age);

        Map<String, Integer> scoreByName = mapper.readValue(file, Map.class);

        File file2 = new File(Resources.getResource("test2.json").getFile());
        MyValue2 myValue2 = mapper.readValue(file2, MyValue2.class);
        System.out.println(myValue2.name + ", " + myValue2.age);
        for (String friend : myValue2.friends) {
            System.out.println(friend);
        }
    }
}
